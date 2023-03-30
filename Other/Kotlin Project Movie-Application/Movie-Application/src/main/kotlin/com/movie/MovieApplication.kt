package com.movie

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class MovieApplication

fun main(args: Array<String>) {
	runApplication<MovieApplication>(*args)
}
