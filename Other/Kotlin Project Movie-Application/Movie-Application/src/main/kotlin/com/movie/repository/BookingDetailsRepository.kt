package com.movie.repository

import com.movie.entity.BookingDetails
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookingDetailsRepository : ReactiveCrudRepository<BookingDetails,Int> {
}