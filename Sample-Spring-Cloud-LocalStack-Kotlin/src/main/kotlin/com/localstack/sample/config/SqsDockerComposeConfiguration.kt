package com.localstack.sample.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.amazonaws.services.sqs.model.CreateQueueRequest
import com.localstack.sample.config.SqsTestcontainersConfiguration.Companion.QUEUE_NAME
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("default")
@Configuration
class SqsDockerComposeConfiguration(
    private val awsSqsClientProperty: AwsSqsClientProperty,
) {

    @Bean
    fun queueMessagingTemplate(amazonSQS: AmazonSQSAsync): QueueMessagingTemplate =
        QueueMessagingTemplate(amazonSQS)

    @Bean
    fun amazonSQS(): AmazonSQSAsync =
        AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(
                    awsSqsClientProperty.endPoint.uri,
                    awsSqsClientProperty.region.static,
                )
            )
            .withCredentials(
                AWSStaticCredentialsProvider(
                    BasicAWSCredentials(
                        awsSqsClientProperty.credentials.accessKey,
                        awsSqsClientProperty.credentials.secretKey,
                    )
                )
            )
            .build()
            .apply {
                createQueue(
                    CreateQueueRequest(QUEUE_NAME)
                        .withAttributes(
                            mapOf(
                                "FifoQueue" to "true",
                                "ContentBasedDeduplication" to "true",
                            )
                        )
                )
            }
}