package com.movie.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.CascadeType
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Document("Theater")
data class Theater(
        @Id
        val id: String,
        val name: String,
        val city: String,
        val State: String,
        val pinCode: Long,
        val address: String,
        val noOfSeats: Long,
        val noOfScreens: Long,
        val costOfTicket: Double,


)
