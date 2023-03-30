package com.movie.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date


@Document("Movie")
data class Movie(
        @Id
        val id: String,
        val movieTitle : String,
        val duration: String,
        val rating: Double,
        val category: String,
        val language: String,
        val movieImage: String,
        val castList: List<String>,
        val releaseDate: Date,
        val noOfWeeks: Long,

//        @DBRef
//        val theater: Theater

)
