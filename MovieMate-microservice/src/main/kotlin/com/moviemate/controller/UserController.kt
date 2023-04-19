package com.moviemate.controller

import com.moviemate.entity.User
import com.moviemate.exception.UserNotFoundException
import com.moviemate.kafka.MovieMateProducer
import com.moviemate.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/user")
class UserController(
        @Autowired
        val userRepository: UserRepository,
        private val movieMateProducer: MovieMateProducer

) {

    @GetMapping("/")
    fun getAllUsers() : Flux<User>{
        return userRepository.findAll()
    }

    @GetMapping("/{mobileNo}")
    fun getUserById(@PathVariable mobileNo : Long) : Mono<User>{
        return userRepository.findById(mobileNo)
    }



    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(@RequestBody user : User) : Mono<User>{
        movieMateProducer.registerUser(user)
        return  userRepository.save(user)

    }


    @PutMapping("/{mobileNo}")
    @ResponseStatus(HttpStatus.OK)
    fun updateUser(@PathVariable mobileNo: Long, @RequestBody user: User): Mono<User>{
        return userRepository.save(user)

    }



    @DeleteMapping("/{mobileNo}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteUserById(@PathVariable mobileNo: Long) : Mono<Void>{
        return userRepository.deleteById(mobileNo)
    }

    @DeleteMapping("/all")
    fun deleteAllUsers() : Mono<Void>{
        return userRepository.deleteAll()
    }

    @PatchMapping("/{mobileNo}/name")
    @ResponseStatus(HttpStatus.OK)
    fun updateUserName(@PathVariable mobileNo: Long, @RequestBody request: Map<String, String>): Mono<User> {
        val newName = request["name"] ?: throw IllegalArgumentException()
        return userRepository.findById(mobileNo)
            .switchIfEmpty(Mono.error(UserNotFoundException("User is not found with Id: $mobileNo")))
            .flatMap { user ->
                user.name = newName
                userRepository.save(user)
            }
    }



    @PatchMapping("/{mobileNo}/password")
    @ResponseStatus(HttpStatus.OK)
    fun updatePassword(@PathVariable mobileNo: Long, @RequestBody request: Map<String, String>) : Mono<User>{
        val newPassword = request["password"] ?: throw IllegalArgumentException()
        return userRepository.findById(mobileNo)
            .switchIfEmpty(Mono.error(UserNotFoundException("User is not found with Id: $mobileNo")))
            .flatMap {
                password -> password.password = newPassword
                userRepository.save(password)
            }
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun useException(ex: UserNotFoundException): ResponseEntity<String>{
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ex.message)
    }

}
