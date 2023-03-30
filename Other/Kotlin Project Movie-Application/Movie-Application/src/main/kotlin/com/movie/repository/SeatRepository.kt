package com.movie.repository

import com.movie.entity.Seat
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SeatRepository : ReactiveCrudRepository<Seat,Long> {
}