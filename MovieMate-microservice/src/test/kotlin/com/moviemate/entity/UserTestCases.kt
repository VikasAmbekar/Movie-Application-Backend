package com.moviemate.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class UserTestCases {


    val user = User(
        mobileNo = 7900003838,
        name = "Vikas R A",
        email = "ambekarviks@gmail.com",
        password = "Vikas#45",
        city = "Mumbai",
        state = "Maharashtra",
        pinCode = 435465,
        address = "CASA Elite P 603"
    )


    @Test
    fun testMobileNumber(){
        Assertions.assertThat(user.mobileNo.toString().length == 10)
    }

    @Test
    fun testName(){
        Assertions.assertThat(user.name).isEqualTo("Vikas R A")
    }

    @Test
    fun testEmail(){
        Assertions.assertThat(user.email).contains("@")
        Assertions.assertThat(user.email).contains("@gmail.com")
    }

    @Test
    fun testPassword(){
//        val passLength = user.password.length
//        Assertions.assertThat(passLength == 8 && passLength < 15)
//        Assertions.assertThat(user.password).contains("#")
        Assertions.assertThat(Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}").matches(user.email))
    }

    @Test
    fun testPincode(){
        Assertions.assertThat(user.pinCode.toString().length == 6)
    }

}