package com.movie.controller

import com.movie.entity.Seat
import com.movie.repository.SeatRepository
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
@RequestMapping("/seats")
class SeatController (
    @Autowired
    val seatRepository: SeatRepository
        ) {

    @GetMapping("/")
    fun getAllSeats():Flux<Seat>{
        return seatRepository.findAll()
    }

    @GetMapping("/{seatId}")
    fun getSeatById(@PathVariable seatId : Long) : Mono<Seat>{
        return seatRepository.findById(seatId)
    }

    @PostMapping("/")
    fun addSeats(@RequestBody seat: Seat):Mono<Seat>{
        return seatRepository.save(seat)
    }

    @PutMapping("/")
    fun updateSeats(@RequestBody seat: Seat):Mono<Seat>{
        return seatRepository.save(seat)
    }

    @DeleteMapping("/{seatId}")
    fun deleteSeatById(@PathVariable seatId: Long):Mono<Void>{
        return seatRepository.deleteById(seatId)
    }
}