package com.moviemate.repository

import com.moviemate.entity.Movie
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository: ReactiveCrudRepository<Movie, String> {

    

}