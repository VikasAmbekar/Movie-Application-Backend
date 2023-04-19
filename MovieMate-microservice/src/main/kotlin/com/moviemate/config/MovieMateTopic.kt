package com.moviemate.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder


@Configuration
class MovieMateTopic {

    @Bean
    fun movieMateTopics(): NewTopic{
    return TopicBuilder.name("moviemate")
        .partitions(2)
        .replicas(3)
        .build()
    }

}