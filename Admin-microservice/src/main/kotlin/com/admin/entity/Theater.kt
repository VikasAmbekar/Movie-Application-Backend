package com.admin.entity


import org.springframework.data.mongodb.core.mapping.Document


@Document("Theater")
data class Theater(

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

