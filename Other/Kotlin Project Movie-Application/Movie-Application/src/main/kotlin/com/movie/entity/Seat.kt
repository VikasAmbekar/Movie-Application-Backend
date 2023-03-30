package com.movie.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("seats")
data class Seat (
    @Id
    val seatId : Long,
    val seatNo : List<String>,
    val user: User
    )
