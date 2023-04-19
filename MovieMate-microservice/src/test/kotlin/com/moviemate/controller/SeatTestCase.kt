package com.moviemate.controller

import com.moviemate.entity.Seat
import com.moviemate.repository.SeatRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SeatTestCase {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var seatRepository: SeatRepository

    @BeforeEach
    fun setup(){
        seatRepository.deleteAll()
    }

    val seat = Seat(
        seatId = 1,
        movieId = "M02",
        theaterId = "T02",
        time = "9:30AM",
        seats = Array(6) { IntArray(6) { 0 } }
    )

    val seat1 = Seat(
        seatId = 2,
        movieId = "M03",
        theaterId = "T03",
        time = "11:30AM",
        seats = Array(6){IntArray(6) {0} }
    )

    @Test
    fun createSeat() {

        webTestClient.post().uri("/seats/")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(seat)
            .exchange()
            .expectStatus().isOk
            .expectBody(Seat::class.java)
            .value { returnedSeat ->
                Assertions.assertThat(returnedSeat.seatId).isEqualTo(1)
                Assertions.assertThat(returnedSeat.movieId).isEqualTo("M02")
                Assertions.assertThat(returnedSeat.theaterId).isEqualTo("T02")
                Assertions.assertThat(returnedSeat.time).isEqualTo("9:30AM")
                Assertions.assertThat(returnedSeat.seats[5][5]).isEqualTo(0)
            }
    }

    @Test
    fun getSeatByMovieTheaterAndTime(){

        webTestClient.get()
            .uri("/seats/{movieId}/{theaterId}/{time}",seat.movieId,seat.theaterId,seat.time)
            .exchange()
            .expectStatus().isOk
            .expectBody(Seat::class.java)
            .returnResult()
            .responseBody
    }

    @Test
    fun getAllSeats() {


        seatRepository.saveAll(listOf(seat,seat1)).collectList().block()

        val response = webTestClient.get().uri("/seats/")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Seat::class.java)
            .returnResult()
            .responseBody


        Assertions.assertThat(response).hasSize(2)
    }



//    @Test
//    fun updateSeats(){
//
//        seatRepository.save(seat)
//
////        val seats2 = arrayOf(
////            intArrayOf(0,0,1,0,0,0),
////            intArrayOf(0,0,1,0,0,0),
////            intArrayOf(0,0,0,0,0,0),
////            intArrayOf(0,0,0,0,0,0),
////            intArrayOf(0,0,0,0,0,0),
////            intArrayOf(0,0,0,0,0,0)
////        )
//
//        val seats2 = Array(6) { IntArray(6) { 0 } }
//        val seats1 ={
//            seat.seats =
//                Array(6) { IntArray(6) { 0 } }
//        }
//
//        val updatedSeat =Seat(
//            seatId = seat.seatId,
//            movieId = "M02",
//            theaterId = "T02",
//            time = "9:30AM",
//            seats = seats2
//        )
//
//
//        webTestClient.patch().uri("/seats/{seatId}",seat.seatId)
//            .contentType(MediaType.APPLICATION_JSON)
//            .bodyValue(seats1)
//            .exchange()
//            .expectStatus().isOk
//            .expectBody(Seat::class.java)
//            .returnResult().responseBody
////            .consumeWith{ result->
////                Assertions.assertThat(result.responseBody).isEqualTo(updatedSeat)
////            }
//    }
//

    @Test
    fun deleteById(){

        val seat = Seat(
            seatId = 1,
            movieId = "M02",
            theaterId = "T02",
            time = "9:30AM",
            seats = Array(6) { IntArray(6) { 0 } }
        )

        seatRepository.save(seat)

        webTestClient.delete().uri("/seats/{seatId}",seat.seatId)
            .exchange()
            .expectStatus().isOk
            .expectBody(Seat::class.java)
            .returnResult()
            .responseBody
    }
}