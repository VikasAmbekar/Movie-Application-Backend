package com.moviemate.controller;

import com.moviemate.entity.Movie
import com.moviemate.repository.MovieRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient;
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieTestCase() {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var movieRepository: MovieRepository

    @BeforeEach
    fun setup(){
        movieRepository.deleteAll();
    }


    val movie1 = Movie(
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

    val movie = Movie(
        id = "M02",
        movieTitle = "Tu Jhoothi Main Makkaar",
        duration="2hr 39min",
        rating=7.7,
        category= listOf("Comedy,Romantic"),
        language= listOf("Hindi"),
        movieImage="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.Egs8JiR6GmtZ7elHB3NOSAHaEK%26pid%3DApi&f=1&ipt=b9f5fc2c35bd0c5865da71f435062992bd303455397dffe1d3dc51f878a2d84a&ipo=images",
        castList = listOf("Ranbir Kapoor","Shraddha Kapoor","Anubhav Singh Bassi"),
        releaseDate = LocalDate.parse("2023-03-30"),
        noOfWeeks = 1
        )

    @Test
    fun createMovie(){
        webTestClient.post().uri("/movie/")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(movie)
            .exchange()
            .expectStatus().isCreated
            .expectBody(Movie::class.java)
            .isEqualTo(movie)
            .returnResult()
    }
    @Test
    fun getMovieById(){
        webTestClient.get().uri("/movie/{M02}", movie.id)
            .exchange()
            .expectStatus().isOk
            .expectBody(Movie::class.java)
            .returnResult()
            .responseBody
    }

    @Test
    fun getAllMovies(){
        movieRepository.saveAll(listOf(movie1,movie)).collectList().block()

        val response = webTestClient.get()
            .uri("/movie/")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Movie::class.java)
            .returnResult().responseBody

        Assertions.assertThat(response).isNotNull
        Assertions.assertThat(response).contains(movie,movie1)
    }

    @Test
    fun updateMovie(){
        movieRepository.save(movie)

        val updatedMovie = Movie(
            id = movie.id,
            movieTitle = "Tu Jhoothi Main Makkaar",
            duration="2hr 39min",
            rating=8.0,
            category= listOf("Comedy","Romantic"),
            language= listOf("Hindi"),
            movieImage="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.Egs8JiR6GmtZ7elHB3NOSAHaEK%26pid%3DApi&f=1&ipt=b9f5fc2c35bd0c5865da71f435062992bd303455397dffe1d3dc51f878a2d84a&ipo=images",
            castList = listOf("Ranbir Kapoor","Shraddha Kapoor","Anubhav Singh Bassi"),
            releaseDate = LocalDate.parse("2023-03-30"),
            noOfWeeks = 2
        )

       val result = webTestClient.put().uri("/movie/{id}",movie.id)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(updatedMovie)
            .exchange()
            .expectStatus().isOk
            .expectBody(Movie::class.java)
            .returnResult().responseBody

            Assertions.assertThat(result).isEqualTo(updatedMovie)
    }

    @Test
    fun deleteMovieById(){
        webTestClient.delete().uri("/movie/{id}",movie.id)
            .exchange()
            .expectStatus().isOk
            .expectBody(Movie::class.java)
            .returnResult()
            .responseBody
    }

}
