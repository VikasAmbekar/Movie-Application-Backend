package com.moviemate.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BookingTestCase {
    val bookingDetails = BookingDetails(
        id = "123456",
        customerName = "Pratik",
        customerMobNo = 7620121554,
        theaterName = "PVR Kurla",
        movieName = "Bhola",
        seatsBooked = listOf("0-1","0-2"),
        numberSeats = 2,
        showTimings = "12:30PM",
        bookingDate = "2023-04-12",
        totalCost = 340,
        email = "pratikwaso@gmail.com"
    )

    @Test
    fun testCustomerName(){
        Assertions.assertThat(bookingDetails.customerName.length >= 2)
    }

    @Test
    fun testCustomerMobileNumber(){
        Assertions.assertThat(bookingDetails.customerMobNo.toString().length == 10)
    }

    @Test
    fun testNumberOfSeats(){
        Assertions.assertThat(bookingDetails.numberSeats == bookingDetails.seatsBooked.size)
    }
}