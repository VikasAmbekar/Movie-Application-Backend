package com.movie.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Document("user")
data class User(
        @Id
        private val mobileNo: Long,
        private val name : String,
        private val email: String,
        private val password: String,
        private val city: String,
        private val state: String,
        private val pinCode: Long,
        private val address: String
)
