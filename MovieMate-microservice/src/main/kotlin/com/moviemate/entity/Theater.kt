package com.moviemate.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document("Theater")
data class Theater(
        @Id
        val id: String,
        val name: String,
        val city: String,
        val state: String,
        val pinCode: Long,
        val address: String,
        val noOfSeats: Long,
        val noOfScreens: Long,
        val costOfTicket: Double,
        val movieList: List<String>
)
