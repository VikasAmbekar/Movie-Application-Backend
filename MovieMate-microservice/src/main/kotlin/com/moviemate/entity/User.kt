package com.moviemate.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document("User")
data class User(
        @Id

        var mobileNo: Long?,
        var name: String,
        var email: String,
        var password: String,
        var city: String,
        var state: String,
        var pinCode: Long,
        var address: String
)
