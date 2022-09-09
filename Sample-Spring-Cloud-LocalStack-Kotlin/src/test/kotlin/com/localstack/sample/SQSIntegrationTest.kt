package com.localstack.sample

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.amazonaws.services.sqs.model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.testcontainers.containers.localstack.LocalStackContainer
import java.util.*


class SQSIntegrationTest : TestContainerContext() {

    @Test
    fun LOCALSTACK_CONTAINER_실행_테스트() {
        assert(localstack.isRunning)
    }

    @Test
    fun `SQS FIFO Queue 단일 메세지 발생 테스트`() {
        val sqs: AmazonSQS = AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(
                        AwsClientBuilder.EndpointConfiguration(
                                localstack.getEndpointOverride(LocalStackContainer.Service.SQS).toString(),
                                localstack.region
                        )
                )
                .withCredentials(
                        AWSStaticCredentialsProvider(
                                BasicAWSCredentials(localstack.accessKey, localstack.secretKey)
                        )
                )
                .build()

        val response: CreateQueueResult = sqs.createQueue(CreateQueueRequest("test-queue.fifo")
                .withAttributes(
                        mapOf(
                                "FifoQueue" to "true",
                                "ContentBasedDeduplication" to "true",
                        )
                ))
        val expectMessage = "hello"
        val expectMessageSize = 1
        val queueUrl = response.queueUrl
        val message = SendMessageRequest(queueUrl, expectMessage).apply {
            withMessageGroupId(generateId())
            withMessageDeduplicationId(generateId())
        }

        // Assert
        sqs.sendMessage(message)
        val messages = sqs.receiveMessage(queueUrl).messages

        // Assertion
        assertAll(
                { assert(expectMessage == messages[0].body) },
                { assert(expectMessageSize == messages.size) },
        )
    }

    @Test
    fun `SQS FIFO Queue 배치 메세지 발행 테스트`() {
        val sqs: AmazonSQS = AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(
                        AwsClientBuilder.EndpointConfiguration(
                                localstack.getEndpointOverride(LocalStackContainer.Service.SQS).toString(),
                                localstack.region
                        )
                )
                .withCredentials(
                        AWSStaticCredentialsProvider(
                                BasicAWSCredentials(localstack.accessKey, localstack.secretKey)
                        )
                )
                .build()

        val response: CreateQueueResult = sqs.createQueue(CreateQueueRequest("test-batch-queue.fifo")
                .withAttributes(
                        mapOf(
                                "FifoQueue" to "true",
                                "ContentBasedDeduplication" to "true",
                        )
                ))
        val expectMessageSize = 5
        val queueUrl = response.queueUrl

        val batchMessage = SendMessageBatchRequest(queueUrl, mutableListOf(
                SendMessageBatchRequestEntry("1", "hello1"),
                SendMessageBatchRequestEntry("2", "hello2"),
                SendMessageBatchRequestEntry("3", "hello3"),
                SendMessageBatchRequestEntry("4", "hello4"),
                SendMessageBatchRequestEntry("5", "hello5"),
        ))

        // Assert
        val batchResult: SendMessageBatchResult = sqs.sendMessageBatch(batchMessage)

        val messageCount = (0 until expectMessageSize).sumOf {
            val messages = sqs.receiveMessage(queueUrl).messages
            messages.size
        }

        // Assertion
        assertAll(
                { assert(batchResult.successful.isNotEmpty()) },
                { assert(expectMessageSize == messageCount) },
        )
    }

    private fun generateId() = UUID.randomUUID().toString()
}