package com.localstack.sample.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.amazonaws.services.sqs.model.CreateQueueRequest
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName

@Configuration
class SQSConfiguration {

    @Profile("default | test")
    @Bean
    fun queueMessagingTemplate(amazonSQS: AmazonSQSAsync): QueueMessagingTemplate =
            QueueMessagingTemplate(amazonSQS)

    @Profile("default | test")
    @Bean
    fun amazonSQS(localStackContainer: LocalStackContainer): AmazonSQSAsync =
            AmazonSQSAsyncClientBuilder.standard()
                    .withEndpointConfiguration(
                            AwsClientBuilder.EndpointConfiguration(
                                    localStackContainer.getEndpointOverride(LocalStackContainer.Service.SQS).toString(),
                                    localStackContainer.region
                            )
                    )
                    .withCredentials(
                            AWSStaticCredentialsProvider(
                                    BasicAWSCredentials(localStackContainer.accessKey, localStackContainer.secretKey)
                            )
                    )
                    .build()
                    .apply {
                        createQueue(CreateQueueRequest(QUEUE_NAME)
                                .withAttributes(
                                        mapOf(
                                                "FifoQueue" to "true",
                                                "ContentBasedDeduplication" to "true",
                                        )
                                ))
                    }

    @Profile("default | test")
    @Bean(initMethod = "start", destroyMethod = "stop")
    fun localStackContainer(): LocalStackContainer =
            LocalStackContainer(DockerImageName.parse("localstack/localstack:1.1.0"))
                    .withServices(
                            LocalStackContainer.Service.SQS
                    )


    companion object {
        const val QUEUE_NAME = "test-queue.fifo"
    }
}