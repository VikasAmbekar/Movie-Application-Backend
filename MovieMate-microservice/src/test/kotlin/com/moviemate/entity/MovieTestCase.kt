package com.moviemate.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieTestCase {

    val movie = Movie(
        id = "M01",
        movieTitle = "John Wick: Chapter 4",
        duration="2hr 51min",
        rating=9.1,
        category= listOf("Action,Thriller"),
        language= listOf("English,Hindi,Tamil"),
        movieImage="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIF.9RwhjGOhw07bSo2mAM6fyg%26pid%3DApi&f=1&ipt=798db637c57faa59c7edd1a851359da818329f0bd1630ef444fa782b5fc335f2&ipo=images",
        castList = listOf("Keanu Reeves","Donnie Yen","Bill Skarsgard"),
        releaseDate = LocalDate.parse("2023-03-24"),
        noOfWeeks = 3
    )

    @Test
    fun testMovieTitle(){
        Assertions.assertThat(movie.movieTitle).contains(String())
        Assertions.assertThat(movie.movieTitle).contains("John")
    }

    @Test
    fun testRating(){
        Assertions.assertThat(movie.rating >= 0.0 && movie.rating <= 10.0)
    }

    @Test
    fun testCategory(){
        Assertions.assertThat(movie.category.isNotEmpty())
    }

    @Test
    fun testLanguage(){
        Assertions.assertThat(movie.language.isNotEmpty())
    }

    @Test
    fun testCastList(){
        Assertions.assertThat(movie.castList.isNotEmpty())
    }

    @Test
    fun testNoOfWeeks(){
        Assertions.assertThat(movie.noOfWeeks <= 10)
    }

}