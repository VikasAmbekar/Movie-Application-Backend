package com.movie.controller

import com.movie.MovieApplication
import com.movie.entity.Movie
import com.movie.repository.MovieRepository
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
@RequestMapping("/movie")
class MovieController(
        @Autowired
        val movieRepository: MovieRepository
) {

    @GetMapping("/")
    fun getAllMovies(): Flux<Movie>{
        return movieRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getMovieById(@PathVariable id : String) : Mono<Movie>{
        return movieRepository.findById(id)
    }

    @PostMapping("/")
    fun addMovie(@RequestBody movie: Movie) :Mono<Movie>{
        return movieRepository.save(movie)
    }

    @PutMapping("/")
    fun updateMovie(@RequestBody movie: Movie) :Mono<Movie>{
        return movieRepository.save(movie)
    }

    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id: String) : Mono<Void>{
        return movieRepository.deleteById(id)
    }

    @DeleteMapping("/all")
    fun deleteAll() : Mono<Void>{
        return movieRepository.deleteAll()
    }
}