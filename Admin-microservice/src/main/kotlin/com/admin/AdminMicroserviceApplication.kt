package com.admin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class AdminMicroserviceApplication

fun main(args: Array<String>) {
	runApplication<AdminMicroserviceApplication>(*args)
}
