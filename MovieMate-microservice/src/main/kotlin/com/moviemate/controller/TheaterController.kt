package com.moviemate.controller


import com.moviemate.entity.Theater
import com.moviemate.repository.TheaterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/theater")
class TheaterController(
        @Autowired
        val theaterRepository: TheaterRepository,


) {
    @GetMapping("/")
    fun getAllTheaters() : Flux<Theater> {
        return theaterRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getTheaterById(@PathVariable id: String) : Mono<Theater> {
        return theaterRepository.findById(id)
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addThreater(@RequestBody theater: Theater) : Mono<Theater>{
        return theaterRepository.save(theater)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTheater(@PathVariable id: String, @RequestBody theater: Theater) : Mono<Theater>{
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


    // Get Theaters By City & Movie
    @GetMapping("/{city}/{movieList}")
    fun getTheatersByCityAndMovieList(
        @PathVariable city: String,
        @PathVariable movieList: List<String>
    ): Flux<Theater> {
        return theaterRepository.findByCityAndMovieListIn(city, movieList)
    }
}

