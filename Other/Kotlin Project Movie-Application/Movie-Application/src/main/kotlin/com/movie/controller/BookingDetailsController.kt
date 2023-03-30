package com.movie.controller

import com.movie.entity.BookingDetails
import com.movie.repository.BookingDetailsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/booking-details")
class BookingDetailsController (
    @Autowired
    val bookingDetailsRepository: BookingDetailsRepository
        ) {

    @GetMapping("/")
    fun getAllBookingDetails():Flux<BookingDetails>{
        return bookingDetailsRepository.findAll()
    }

    @GetMapping("/{bookingId}")
    fun getBookingDetailsById(@PathVariable bookingId : Int) : Mono<BookingDetails>{
        return bookingDetailsRepository.findById(bookingId)
    }

    @PostMapping("/")
    fun addBookingDetails(@RequestBody bookingDetails: BookingDetails):Mono<BookingDetails>{
        return bookingDetailsRepository.save(bookingDetails)
    }

    @PutMapping("/")
    fun updateBookingDetails(@RequestBody bookingDetails: BookingDetails):Mono<BookingDetails>{
        return bookingDetailsRepository.save(bookingDetails)
    }

    @DeleteMapping("/{bookingId}")
    fun deleteBookingDetails(@PathVariable bookingId: Int):Mono<Void>{
        return bookingDetailsRepository.deleteById(bookingId)
    }
}