package com.moviemate.controller

import com.moviemate.entity.Movie
import com.moviemate.exception.MovieNotFoundException
import com.moviemate.repository.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@CrossOrigin("http://localhost:3000/")
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
            .switchIfEmpty(Mono.error(MovieNotFoundException("Movie is not found with ID: $id")))
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addMovie(@RequestBody movie: Movie) :Mono<Movie>{
        return movieRepository.save(movie)
    }

    @PutMapping("/{id}")
    fun updateMovie(@PathVariable id: String, @RequestBody movie: Movie) : Mono<Movie>{
        return movieRepository.save(movie)

    }

    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id: String) : Mono<Void>{
        return movieRepository.deleteById(id);
    }

    @DeleteMapping("/all")
    fun deleteAll() : Mono<Void>{
        return movieRepository.deleteAll()
    }

    @ExceptionHandler(MovieNotFoundException::class)
    fun movieNotFound(ex: MovieNotFoundException): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ex.message)
    }
}