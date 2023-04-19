package com.moviemate.repository

import com.moviemate.entity.Seat
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface SeatRepository : ReactiveCrudRepository<Seat,Long> {

    @Query("{'movieId':?0,'theaterId':?1,'time':?2}")
    fun getSeatsByDateAndTime(movieId:String,theaterId:String,time:String): Mono<Seat>
}