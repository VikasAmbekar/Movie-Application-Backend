package com.moviemate.controller

import com.moviemate.entity.User
import com.moviemate.kafka.MovieMateProducer
import com.moviemate.repository.UserRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.test.StepVerifier
import java.time.Duration



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTestCase() {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var movieMateProducer: MovieMateProducer

    // The setUp() method annotated with @BeforeEach
    // This is used to clean the test environment to avoid the side impact of previous test cases
    // with help of deleteAll in repository we're  deleting all previous test cases

    @BeforeEach
    fun setup(){
        userRepository.deleteAll().block()
        webTestClient = webTestClient.mutate()
            .responseTimeout(Duration.ofMillis(30000))
            .build()
    }

    val user = User(
        mobileNo = 7900001883,
        name = "Vikas Ambekar",
        email = "ambekarviks@gmail.com",
        password = "Vikas@23",
        city = "Mumbai",
        state = "Maharashtra",
        pinCode = 421204,
        address = "Flat no 602, CASA Elite P Wing"
    )




    @Test
    fun createUser() {
        webTestClient.post()
            .uri("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(user)
            .exchange()
            .expectStatus().isCreated
            .expectBody(User::class.java)
            .isEqualTo(user)

         movieMateProducer.registerUser(user)
    }

    @Test
    fun getUserById(){

        webTestClient.get().uri("/user/{mobileNo}", user.mobileNo)
            .exchange()
            .expectStatus().isOk
            .expectBody(User::class.java)
            .returnResult()
            .responseBody
    }

    @Test
    fun updateUser(){


        val createUser = userRepository.save(user).block()

        val updatedUser = User(
            mobileNo = createUser?.mobileNo,
            name = "Vikas",
            email = "ambekarviks@gmail.com",
            password = "Vikas@23",
            city = "Mumbai",
            state = "Maharashtra",
            pinCode = 421204,
            address = "Flat no 602, CASA Elite P Wing"
        )

        webTestClient.put().uri("/user/{mobileNo}", updatedUser.mobileNo)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(updatedUser)
            .exchange()
            .expectStatus().isOk
            .expectBody(User::class.java)
            .consumeWith {
                    result ->
                Assertions.assertThat(result.responseBody).isEqualTo(updatedUser)
            }
    }


    val user2 = User(
        mobileNo = 9123456789,
        name = "Pratik Waso",
        email = "pratik@example.com",
        password = "Pratik@23",
        city = "Pune",
        state = "Maharashtra",
        pinCode = 411033,
        address = "Hinjewadi, pune"
    )

    @Test
    fun getAllUsers() {
        userRepository.saveAll(listOf(user, user2)).collectList().block()

        val response = webTestClient.get().uri("/user/")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(User::class.java)
            .returnResult()
            .responseBody

        Assertions.assertThat(response).hasSize(2)
        Assertions.assertThat(response).contains(user, user2)
    }


    @Test
    fun deleteUserById(){
        webTestClient.delete().uri("/user/{id}", user.mobileNo)
            .exchange()
            .expectStatus().isOk
            .expectBody(User::class.java)
            .returnResult()
            .responseBody
    }

    @Test
    fun deleteAllUsers(){
        userRepository.saveAll(listOf(user, user2)).collectList().block()

        webTestClient.delete().uri("/user/all")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(User::class.java)
            .returnResult()
            .responseBody

        val users = userRepository.findAll().collectList().block()
        Assertions.assertThat(users).isEmpty()
    }



}




