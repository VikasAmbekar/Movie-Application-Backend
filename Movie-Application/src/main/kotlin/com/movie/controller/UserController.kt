package com.movie.controller

import com.movie.entity.User
import com.movie.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/user")
class UserController(
        @Autowired
        val userRepository: UserRepository

) {

    @GetMapping("/")
    fun getAllUsers() : Flux<User>{
        return userRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable mobileNo : Long) : Mono<User>{
        return userRepository.findById(mobileNo)
    }

    @PostMapping("/")
    fun addUser(@RequestBody user : User) : Mono<User>{
        return  userRepository.save(user)

    }

    @PutMapping("/")
    fun updateUser(@RequestBody user: User): Mono<User>{
        return userRepository.save(user)

    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable mobileNo: Long) : Mono<Void>{
        return userRepository.deleteById(mobileNo)
    }

    @DeleteMapping("/all")
    fun deleteAllUsers() : Mono<Void>{
        return userRepository.deleteAll()
    }
}
