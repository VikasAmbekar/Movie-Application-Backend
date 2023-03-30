package com.movie.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document("Movie")
data class Movie(
        @Id
        private val id: String,
        private val movieTitle : String,
        private val duration: String,
        private val rating: Double,
        private val category: String,
        private val language: String,
        private val movieImage: String,
        private val castList: List<String>,
        private val releaseDate: Date,
        private val noOfWeeks: Long
)
