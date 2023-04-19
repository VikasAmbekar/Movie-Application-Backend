package com.moviemate.controller

import com.moviemate.entity.Seat
import com.moviemate.repository.SeatRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/seats")
class SeatController (
    @Autowired
    val seatRepository: SeatRepository
) {

    @GetMapping("/")
    fun getSeats():Flux<Seat>{
        return seatRepository.findAll();
    }

    @GetMapping("/{movieId}/{theaterId}/{time}")
    fun getSeatsByDateAndTime(
        @PathVariable movieId:String,
        @PathVariable theaterId:String,
        @PathVariable time:String
    ): Mono<Seat>{
        return seatRepository.getSeatsByDateAndTime(movieId,theaterId,time );
    }

    @PostMapping("/")
    fun addSeats(@RequestBody seat:Seat):Mono<Seat>{

        return seatRepository.save(seat);
    }


    @PatchMapping("/{seatId}")
    fun updateSeats(@PathVariable seatId: Long, @RequestBody request: Map<String, Array<IntArray>>): Mono<Seat> {
        val newSeat=request["seats"] ?: throw IllegalArgumentException("Invalid Seats")
        return seatRepository.findById(seatId)
            .flatMap { seat ->
                seat.seats=newSeat
                seatRepository.save(seat)}
    }

    @DeleteMapping("/{seatId}")
    fun deleteSeatById(@PathVariable seatId: Long) : Mono<Void>{
        return seatRepository.deleteById(seatId)
    }

}