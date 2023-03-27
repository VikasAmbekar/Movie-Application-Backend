package com.movie.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Document("User")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val mobileNo: Long,
        val name : String,
        val email: String,
        val password: String,
        val city: String,
        val state: String,
        val pinCode: Long,
        val address: String
)
