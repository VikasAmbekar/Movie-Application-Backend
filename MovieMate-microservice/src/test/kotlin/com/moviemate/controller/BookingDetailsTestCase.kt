package com.moviemate.controller

import com.moviemate.entity.BookingDetails
import com.moviemate.kafka.MovieMateProducer
import com.moviemate.repository.BookingDetailsRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.Duration

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingDetailsTestCase {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var bookingDetailsRepository: BookingDetailsRepository

    @Autowired
    private lateinit var movieMateProducer: MovieMateProducer

    @BeforeEach
    fun setup(){
        bookingDetailsRepository.deleteAll()
        webTestClient = webTestClient.mutate()
            .responseTimeout(Duration.ofMillis(30000))
            .build()
    }

    val details = BookingDetails(
        id = "1",
        customerName = "Vikas Ambekar",
        customerMobNo = 9284408566,
        theaterName = "PVR",
        movieName="Kantara",
        seatsBooked = listOf("0-0","0-1"),
        numberSeats = 2,
        showTimings = "3:00PM",
        bookingDate = "2023-05-10",
        totalCost = 320,
        email = "ambekarviks13@gmail.com"
    )

    @Test
    fun createInvoice(){

        movieMateProducer.bookingDetails(details)

        webTestClient.post().uri("/booking-details/")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(details)
            .exchange()
            .expectStatus().isOk
            .expectBody(BookingDetails::class.java)
            .isEqualTo(details)
            .returnResult()
    }

    @Test
    fun getBookingDetailsById(){


        webTestClient.get().uri("/booking-details/{bookingId}",details.id)
            .exchange()
            .expectStatus().isOk
            .expectBody(BookingDetails::class.java)
            .returnResult()
            .responseBody
    }


    val details2 = BookingDetails(
        id = "2",
        customerName = "Vikas Pratik",
        customerMobNo = 8975950533,
        theaterName = "Miraj Cinemas",
        movieName = "John Wick Chapter 4",
        seatsBooked = listOf("5-3","5-4"),
        numberSeats = 2,
        showTimings = "9:00PM",
        bookingDate = "2023-06-12",
        totalCost = 320,
        email = "ambekarviks11@gmail.com"
    )

        @Test
    fun getAllBookings(){

        bookingDetailsRepository.saveAll(listOf(details,details2)).collectList().block()

        val response = webTestClient.get().uri("/booking-details/")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(BookingDetails::class.java)
            .returnResult()
            .responseBody

        Assertions.assertThat(response).isNotNull
        Assertions.assertThat(response).hasSize(2)
        Assertions.assertThat(response).contains(details,details2)

    }

    @Test
    fun updateBooking(){


        bookingDetailsRepository.save(details)

        val updateDetails = BookingDetails(
            id = details.id,
            customerName = "Vikas Ambekar",
            customerMobNo = 9284408566,
            theaterName = "PVR Laser",
            movieName = "Kantara : A Legend",
            seatsBooked = listOf("0-0","0-1"),
            numberSeats = 2,
            showTimings = "03:00PM",
            bookingDate = "2023-07-07",
            totalCost = 320,
            email = "ambekarviks12@gmail.com"
        )

        webTestClient.put().uri("/booking-details/{id}",updateDetails.id)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(updateDetails)
            .exchange()
            .expectStatus().isOk
            .expectBody(BookingDetails::class.java)
            .consumeWith { result ->
                Assertions.assertThat(result.responseBody).isEqualTo(updateDetails)
            }
    }

    @Test
    fun deleteBookingById(){


        webTestClient.delete().uri("/booking-details/{id}",details.id)
            .exchange()
            .expectStatus().isOk
            .expectBody(BookingDetails::class.java)
            .returnResult()
            .responseBody
    }

    @Test
    fun deleteAllBookings(){


        bookingDetailsRepository.saveAll(listOf(details,details2)).collectList().block()

        webTestClient.delete().uri("/booking-details/all")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Void::class.java)

        val bookings = bookingDetailsRepository.findAll().collectList().block()

        Assertions.assertThat(bookings).isEmpty()
    }

}

