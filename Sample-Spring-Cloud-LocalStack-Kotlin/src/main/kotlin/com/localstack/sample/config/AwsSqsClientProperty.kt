package com.localstack.sample.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Profile

@Profile("default")
@ConstructorBinding
@ConfigurationProperties(prefix = "cloud.aws")
data class AwsSqsClientProperty(
    val region: Region,
    val credentials: Credentials,
    val endPoint: EndPoint,
)

data class Region(val static: String, val auto: Boolean)
data class Credentials(val accessKey: String, val secretKey: String)
data class EndPoint(val uri: String)