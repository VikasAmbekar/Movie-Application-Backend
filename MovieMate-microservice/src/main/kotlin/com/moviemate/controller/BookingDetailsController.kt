package com.moviemate.controller

import com.moviemate.entity.BookingDetails
import com.moviemate.kafka.MovieMateProducer
import com.moviemate.repository.BookingDetailsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
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

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/booking-details")
class BookingDetailsController (
    @Autowired
    val bookingDetailsRepository: BookingDetailsRepository,

    val movieMateProducer: MovieMateProducer
) {

    @GetMapping("/")
    fun getAllBookingDetails():Flux<BookingDetails>{
        return bookingDetailsRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getBookingDetailsById(@PathVariable id : String) : Mono<BookingDetails>{
        return bookingDetailsRepository.findById(id)
    }

    @PostMapping("/")
    fun addBookingDetails(@RequestBody bookingDetails: BookingDetails):Mono<BookingDetails>{
        movieMateProducer.bookingDetails(bookingDetails)
        return bookingDetailsRepository.save(bookingDetails)
    }

    @PutMapping("/{id}")
    fun updateBookingDetails(@PathVariable id: String, @RequestBody bookingDetails: BookingDetails):Mono<BookingDetails>{
        return bookingDetailsRepository.save(bookingDetails)
    }

    @DeleteMapping("/{id}")
    fun deleteBookingDetails(@PathVariable id: String):Mono<Void>{
        return bookingDetailsRepository.deleteById(id)
    }

    @GetMapping("/mobile/{customerMobNo}")
    fun findBookingDetailsByMobileNo(@PathVariable customerMobNo: Long): Flux<BookingDetails>{
        return bookingDetailsRepository.getBookingDetailsByMobileNo(customerMobNo)
    }


    @DeleteMapping("/all")
    fun deleteAll():Mono<Void>{
        return bookingDetailsRepository.deleteAll()
    }
}