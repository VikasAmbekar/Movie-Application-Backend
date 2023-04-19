package com.admin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class RestTemplateConfig {
    @Bean
    fun restTemplate():RestTemplate{
        return RestTemplate()
    }

}
