package com.moviemate.repository

import com.moviemate.entity.Theater
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface TheaterRepository: ReactiveCrudRepository<Theater, String> {
    @Query("{'city': ?0, 'movieList': {\$in: ?1}}")
    fun findByCityAndMovieListIn(city: String, movieList: List<String>): Flux<Theater>
}
