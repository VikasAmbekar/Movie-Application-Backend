package com.moviemate.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType


@Document("Booking")
data class BookingDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: String? = null,
    val customerName: String,
    val customerMobNo : Long,
    val theaterName: String,
    val movieName: String,
    val seatsBooked: List<String>,
    val numberSeats: Int,
    val showTimings: String,
    val bookingDate: String,
    val totalCost: Int,
    val email: String

)

