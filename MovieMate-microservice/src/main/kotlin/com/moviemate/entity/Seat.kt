package com.moviemate.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Seats")
data class Seat (
    @Id
    var seatId: Long = 0,
//        get() = field
//        set(value) {
//            field = value
//        }

    var movieId: String = "",
//        get() = field
//        set(value) {
//            field = value
//        }

    var theaterId: String = "",
//        get() = field
//        set(value) {
//            field = value
//        }

    var time: String = "",
//        get() = field
//        set(value) {
//            field = value
//        }

    var seats: Array<IntArray> = Array(6){IntArray(6) {0} }
//        get() = field
//        set(value) {
//            field = value
//        }


)