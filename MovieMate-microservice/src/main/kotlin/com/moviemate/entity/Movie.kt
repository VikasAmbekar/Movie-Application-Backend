package com.moviemate.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate


@Document("Movie")
data class Movie(
        @Id
        var id: String,
        var movieTitle : String,
        var duration: String,
        var rating: Double,
        var category: List<String>,
        var language: List<String>,
        var movieImage: String,
        var castList: List<String>,
        var releaseDate: LocalDate,
        var noOfWeeks: Long,

)
