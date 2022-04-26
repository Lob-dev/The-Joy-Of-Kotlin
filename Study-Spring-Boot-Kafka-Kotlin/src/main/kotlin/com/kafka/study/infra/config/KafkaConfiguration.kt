package com.kafka.study.infra.config

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer


@Configuration
class KafkaConfiguration {
    val localHost: String = "127.0.0.1:9092"
    val defaultRetries: Int = 0
    val defaultBatchSize: Int = 0
    val defaultBufferSize: Int = 40960
    val defaultAutoOffsetReset: String = "earliest"
    val defaultGroupId: String = "default"

    @Autowired
    lateinit var environment: Environment

    @Bean
    fun producerFactory(): ProducerFactory<String, Any> {
        val configs: MutableMap<String, Any> = HashMap()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = environment.getProperty("bootstrap-servers")?: localHost
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ProducerConfig.RETRIES_CONFIG] = environment.getProperty("retries")?: defaultRetries
        configs[ProducerConfig.BATCH_SIZE_CONFIG] = environment.getProperty("batch-size")?: defaultBatchSize
        configs[ProducerConfig.BUFFER_MEMORY_CONFIG] = environment.getProperty("buffer-memory")?: defaultBufferSize
        return DefaultKafkaProducerFactory(configs)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Any> = KafkaTemplate(producerFactory())

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> {
        val configs: MutableMap<String, Any> = HashMap()
        configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = environment.getProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG)?: localHost
        configs[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configs[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configs[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = defaultAutoOffsetReset
        configs[ConsumerConfig.GROUP_ID_CONFIG] = defaultGroupId
        return DefaultKafkaConsumerFactory(configs)
    }

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Any>> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.consumerFactory = consumerFactory()
        return factory
    }

}