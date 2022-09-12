package com.localstack.sample.domain

import com.localstack.sample.config.SQSConfiguration.Companion.QUEUE_NAME
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import io.awspring.cloud.messaging.core.SqsMessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service


@Service
class SampleSQSPublisher(
        private val queueMessagingTemplate: QueueMessagingTemplate,
) {

    fun doSend(): Unit = queueMessagingTemplate.send(QUEUE_NAME, MessageBuilder.withPayload("hello")
            .copyHeaders(mapOf(
                    SqsMessageHeaders.SQS_GROUP_ID_HEADER to "1",
                    SqsMessageHeaders.SQS_DEDUPLICATION_ID_HEADER to "2",
            ))
            .build())
}