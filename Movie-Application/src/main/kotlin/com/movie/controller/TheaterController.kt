package com.movie.controller

import com.movie.entity.Theater
import com.movie.repository.TheaterRepository
import org.springframework.beans.factory.annotation.Autowired
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
@RequestMapping("/theater")
class TheaterController(
        @Autowired
        val theaterRepository: TheaterRepository
) {
    @GetMapping("/")
    fun getAllTheaters() : Flux<Theater>{
        return theaterRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getTheaterById(@PathVariable id: String) : Mono<Theater>{
        return theaterRepository.findById(id)
    }

    @PostMapping("/")
    fun addThreater(@RequestBody theater: Theater) : Mono<Theater>{
        return theaterRepository.save(theater)
    }

    @PutMapping("/")
    fun updateTheater(@RequestBody theater: Theater) : Mono<Theater>{
        return theaterRepository.save(theater)
    }

    @DeleteMapping("/{id}")
    fun deleteTheater(@PathVariable id : String) : Mono<Void>{
        return theaterRepository.deleteById(id)
    }

    @DeleteMapping("/all")
    fun deleteAllTheaters() : Mono<Void>{
        return theaterRepository.deleteAll()
    }
}
