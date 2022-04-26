package com.kafka.study.infra.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("spring.kafka.consumer")
data class KafkaConsumerResources (
    val bootstrapServers: String,
    val keyDeserializer: String,
    val valueDeserializer: String,
    val properties: Properties
)

@ConstructorBinding
@ConfigurationProperties("spring.kafka.producer")
data class KafkaProducerResources (
    val bootstrapServers: String,
    val keySerializer: String,
    val valueSerializer: String,
    val properties: Properties
)

data class Properties (
    val interceptor: Interceptor
)

data class Interceptor (
    val classes: String
)