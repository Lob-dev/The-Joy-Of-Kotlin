package com.kafka.study.infra.consumer

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.time.Duration.ofSeconds
import java.util.logging.Logger

@Component
class FirstKafkaConsumer {
    private var log = LoggerFactory.getLogger(FirstKafkaConsumer::class.java)

    /*
    fun consumeLegacy() {
        kafkaConsumer.subscribe(listOf("sample-topic"))
        while (true) {
            val records = kafkaConsumer.poll(ofSeconds(3L))
            for (record in records) {
                log.info("$record")
            }
        }
    }
    */

    @KafkaListener(topics = ["sample-topic"], containerFactory = "kafkaListenerContainerFactory", groupId = "default")
    fun consume(records: ConsumerRecords<String, Any>) {
        for (record in records) {
            log.info("$record")
        }
    }

}