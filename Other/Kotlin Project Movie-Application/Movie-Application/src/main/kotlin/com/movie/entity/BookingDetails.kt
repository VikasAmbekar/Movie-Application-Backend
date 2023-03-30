package com.movie.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document("booking-details")
data class BookingDetails(
    @Id
    val bookingId:Int,
    val customerName: String,
    val theatreName: String,
    val seatsBooked: List<String>,
    val numberSeats: Int,
    val showTimings: String,
    val bookingDate: Date
)