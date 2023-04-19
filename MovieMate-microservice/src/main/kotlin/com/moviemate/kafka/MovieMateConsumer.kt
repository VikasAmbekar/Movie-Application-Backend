package com.moviemate.kafka

import com.moviemate.entity.BookingDetails
import com.moviemate.entity.User
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MovieMateConsumer {

    private val logger = LoggerFactory.getLogger(MovieMateConsumer::class.java)

    @KafkaListener(topics = ["moviemate"], groupId = "moviemate-group")
    fun receivedUser(user: User) {
        logger.info("Received user: $user")
    }

    fun bookingDetailsReceived(bookingDetails: BookingDetails){
        logger.info("New booking details received: $bookingDetails")
    }
}