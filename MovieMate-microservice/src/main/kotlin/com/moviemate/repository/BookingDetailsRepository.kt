package com.moviemate.repository

import com.moviemate.entity.BookingDetails
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface BookingDetailsRepository : ReactiveCrudRepository<BookingDetails,String> {


    @Query("{ 'customerMobNo' : ?0}")
    fun getBookingDetailsByMobileNo(customerMobNo: Long): Flux<BookingDetails>


}