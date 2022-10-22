package com.localstack.sample.domain

import com.localstack.sample.config.SqsTestcontainersConfiguration.Companion.QUEUE_NAME
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy
import io.awspring.cloud.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Service

@Service
class SampleSQSSubscriber {

    @SqsListener(value = [QUEUE_NAME], deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    fun doHandle(message: String): Unit = println("message = $message")
}