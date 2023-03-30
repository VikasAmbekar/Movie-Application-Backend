package com.movie.repository

import com.movie.entity.Theater
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TheaterRepository: ReactiveCrudRepository<Theater, String> {
}
