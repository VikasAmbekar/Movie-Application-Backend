package com.movie.repository

import com.movie.entity.Movie
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository: ReactiveCrudRepository<Movie, String> {
}