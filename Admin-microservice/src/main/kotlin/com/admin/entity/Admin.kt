package com.admin.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Admin")
data class Admin(
    @Id
    val adminMobileNo : Long,
    val adminName: String,
    val adminEmailId: String,
    val adminPassword: String
)
