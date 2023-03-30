package com.movie.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Theater")
data class Theater(
        @Id
        private val id: String,
        private val name: String,
        private val city: String,
        private val State: String,
        private val pinCode: Long,
        private val address: String,
        private val noOfSeats: Long,
        private val noOfScreens: Long,
        private val costOfTicket: Double,
        private val moviesList: List<Movie>
)
