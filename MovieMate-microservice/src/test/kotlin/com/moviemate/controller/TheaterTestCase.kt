package com.moviemate.controller

import com.moviemate.entity.Theater
import com.moviemate.repository.TheaterRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TheaterTestCase {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var theaterRepository: TheaterRepository

    @BeforeEach
    fun setup(){
        theaterRepository.deleteAll()
    }

    val theater = Theater(
        id = "T01",
        name = "Xperia PVR",
        city = "Dombivali East",
        state = "Maharashtra",
        pinCode = 421204,
        address = "Lodha Palava City Phase II",
        noOfSeats = 60,
        noOfScreens = 2,
        costOfTicket = 150.0,
        movieList = listOf("M01", "M02")
    )

    @Test
    fun createTheater() {


        webTestClient.post().uri("/theater/")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(theater)
            .exchange()
            .expectStatus().isCreated
            .expectBody(Theater::class.java)
            .isEqualTo(theater)
            .returnResult()
    }


    @Test
    fun getTheaterById(){


        webTestClient.get().uri("/theater/{id}", theater.id)
            .exchange()
            .expectStatus().isOk
            .expectBody(Theater::class.java)
            .returnResult()
            .responseBody
    }

    val theater2 = Theater(
        id = "T02",
        name = "Xperia PVR I",
        city = "Dombivali West",
        state = "Maharashtra",
        pinCode = 421204,
        address = "Lodha Palava  Phase I",
        noOfSeats = 60,
        noOfScreens = 2,
        costOfTicket = 150.0,
        movieList = listOf("M01", "M02")
    )

    @Test
    fun getAllTheaters() {



        theaterRepository.saveAll(listOf(theater, theater2)).collectList().block()

        val response = webTestClient.get().uri("/theater/")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Theater::class.java)
            .returnResult().responseBody


        Assertions.assertThat(response).isNotNull
        Assertions.assertThat(response).hasSize(2)
        Assertions.assertThat(response).contains(theater, theater2)
    }


    @Test
    fun updateTheater(){

        theaterRepository.save(theater)

        val updatedTheater = Theater(
            id = "T01",
            name = "Xperia PVR I",
            city = "Dombivali Main",
            state = "Maharashtra",
            pinCode = 421204,
            address = "Lodha  Phase II",
            noOfSeats = 60,
            noOfScreens = 2,
            costOfTicket = 150.0,
            movieList = listOf("M01", "M02")
        )

        webTestClient.put().uri("/theater/{id}", updatedTheater.id)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(updatedTheater)
            .exchange()
            .expectStatus().isOk
            .expectBody(Theater::class.java)
            .consumeWith { result ->
                Assertions.assertThat(result.responseBody).isEqualTo(updatedTheater)
            }
    }

    @Test
    fun deleteTheaterById(){


        webTestClient.delete().uri("/theater/{id}", theater.id)
            .exchange()
            .expectStatus().isOk
            .expectBody(Theater::class.java)
            .returnResult()
            .responseBody
    }


    @Test
    fun deleteAllTheaters() {


        theaterRepository.saveAll(listOf(theater, theater2)).collectList().block()

        webTestClient.delete().uri("/theater/all")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Void::class.java)

        val theaters = theaterRepository.findAll().collectList().block()

        Assertions.assertThat(theaters).isEmpty()
    }


}
