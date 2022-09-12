package com.localstack.sample.interfaces

import com.localstack.sample.domain.SampleSQSPublisher
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/messages")
class SampleSQSAPI(
        private val sampleSQSPublisher: SampleSQSPublisher,
) {

    @PostMapping
    fun sendMessage() = sampleSQSPublisher.doSend()
}