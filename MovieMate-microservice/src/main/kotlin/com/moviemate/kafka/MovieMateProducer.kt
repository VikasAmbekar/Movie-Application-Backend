package com.moviemate.kafka

import com.moviemate.entity.BookingDetails
import com.moviemate.entity.User
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import javax.mail.internet.MimeMessage

@Service
class MovieMateProducer(
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, User>,
    private val kafkaTemplate1: KafkaTemplate<String, BookingDetails>,
    private val javaMailSender: JavaMailSender


) {

    private val logger = LoggerFactory.getLogger(MovieMateProducer::class.java)

    fun sendWelcomeEmail(mailId:String,emailSubject:String,emailBody:String){
        val message: MimeMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message,true)
        helper.setFrom("moviemate.axis1@gmail.com")
        helper.setTo(mailId)
        helper.setSubject(emailSubject)
        helper.setText(emailBody)
        javaMailSender.send(message)
    }

    fun registerUser(user: User){
        logger.info(String.format("New user registered: $user"))
//        val message : Message<User> = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC, "moviemate").build()
        kafkaTemplate.send("moviemate", user.mobileNo.toString(), user)
        sendWelcomeEmail(user.email,"Welcome to MovieMate!","Dear ${user.name}, \n\n Your registration is sucessfull with MovieMate. \n Your username is ${user.mobileNo} \n\n Best Regards, \n  MovieMate Team")
    }


    fun bookingDetails(bookingDetails: BookingDetails){
        logger.info(String.format("New booking details are added $bookingDetails"))
        kafkaTemplate1.send("moviemate", bookingDetails.id.toString(), bookingDetails)
        sendWelcomeEmail(bookingDetails.email,"Booking details are confirmed for ${bookingDetails.movieName}", " Dear ${bookingDetails.customerName}, \n\n Your booking details are confirmed, the ticket details are given below \n Movie name: ${bookingDetails.movieName} \n  Show time:  ${bookingDetails.showTimings} \n Date: ${bookingDetails.bookingDate} \n Number of seats: ${bookingDetails.numberSeats} \n Seat numbers: ${bookingDetails.seatsBooked} \n  Total cost: ₹ ${bookingDetails.totalCost} /- \n Theater name: ${bookingDetails.theaterName} \n\n Enjoy the show! \n\n Best Regards, \n MovieMate Team")

    }
}
