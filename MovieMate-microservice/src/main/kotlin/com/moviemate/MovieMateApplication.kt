package com.moviemate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class MovieMateApplication

fun main(args: Array<String>) {
	runApplication<MovieMateApplication>(*args)
}
