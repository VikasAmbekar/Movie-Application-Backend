package com.movie

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier


class MovieApplicationTests {

	data class User(
		val mobileNo: Long,
		val name : String,
		val email: String,
		val password: String,
		val city: String,
		val state: String,
		val pinCode: Long,
		val address: String
	)

	val user1 = User(7900001883,"Vikas Ambekar","vikas.ambekar@gmail.com","Vikas@123",
		"Mumbai","Maharashtra",401209,
		"E/102, Sita Complex")

	@Test
	fun passwordTest(){
		val passwords = Flux.just("Vikas@123","Abcedefg#1114")
			.log()
//			.filter{pass -> pass.length == 8}
			.filter{pass -> pass.contains(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}$"))}

		//val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}$")
		//return passwordRegex.matches(user1.password)

		StepVerifier.create(passwords.log())
			.expectNext("Vikas@123","Abcedefg#1114")
			.verifyComplete()

	}

	data class Theater(
		val id: String,
		val name: String,
		val city: String,
		val State: String,
		val pinCode: Long,
		val address: String,
		val noOfSeats: Long,
		val noOfScreens: Long,
		val costOfTicket: Double,
		val seatsBooked: Long
	)


	val t1 = Theater("T1","Theater1","Mumbai","Maharashtra",401209,"NSP East",125,
		2,140.0,120)

	@Test
	fun noOfTSeatsTest(){
		val theater = Flux.just(t1)
			.log()
			.filter{ t1 -> t1.noOfSeats == t1.noOfSeats}
			.map{t1.noOfSeats >= t1.seatsBooked}

		StepVerifier.create(theater.log())
			.expectNext(true)
			.verifyComplete()
	}

	val cities = listOf<String>("Mumbai","Pune","Bangalore","Delhi","Kolkata")


	@Test
	fun cityVerifyTest(){
		val matching = Flux.just(t1)
			.log()
			.map{ t1.city == cities[0]}


		StepVerifier.create(matching)
			.expectNext(true)
			.verifyComplete()
	}


}
