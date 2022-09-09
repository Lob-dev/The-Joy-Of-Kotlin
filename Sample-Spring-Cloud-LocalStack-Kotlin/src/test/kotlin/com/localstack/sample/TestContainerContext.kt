package com.localstack.sample

import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
open class TestContainerContext {

    @Container
    protected val localstack: LocalStackContainer = LocalStackContainer(DockerImageName.parse("localstack/localstack:0.11.3"))
            .withServices(
                    LocalStackContainer.Service.SQS,
            )
}