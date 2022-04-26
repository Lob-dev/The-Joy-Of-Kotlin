package com.kafka.study.infra.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class FirstKafkaProducer(
    val kafkaTemplate: KafkaTemplate<String, Any>
) {

}