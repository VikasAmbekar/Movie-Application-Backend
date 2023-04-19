package com.moviemate.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TheaterTestCase {

    val theater = Theater(
        id = "T01",
        name = "Plaza Cinema",
        city = "Mumbai",
        state = "Maharashtra",
        pinCode = 400028,
        address = "Dadar West Mumbai",
        noOfSeats = 36,
        noOfScreens = 3,
        costOfTicket = 140.0,
        movieList = listOf("M01","M02")

    )

    @Test
    fun testTheaterTitle(){
        Assertions.assertThat(theater.name).contains(String())
        Assertions.assertThat(theater.name).contains("Plaza Cinema")
    }

    @Test
    fun testTheaterCity(){
        val city = listOf("Mumbai","Pune","Kolkata","Delhi","Banglore")
        Assertions.assertThat(theater.city.filter { city.contains(theater.city) })
    }

    @Test
    fun testTheaterState(){
        val State = listOf("Maharashtra","West Bengal","Karnataka","Delhi")
        Assertions.assertThat(theater.state.filter { State.contains(theater.state) })
    }

    @Test
    fun testPincode(){
        Assertions.assertThat(theater.pinCode.toString().length == 6)
    }

    @Test
    fun testNoOfSeats(){
        Assertions.assertThat(theater.noOfSeats <=250)
    }

}