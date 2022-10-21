package com.sample.batch.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@EnableScheduling
@Configuration
class SchedulerConfiguration {

    @Bean
    fun taskExecutor(): ThreadPoolTaskExecutor =
        ThreadPoolTaskExecutor().apply {
            corePoolSize = 5
            maxPoolSize = 5
            queueCapacity = 100
            setThreadNamePrefix("scheduler-")
            initialize()
        }
}