package com.sample.batch

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class SampleJobScheduler (
    @Qualifier("sampleJob") private val job: Job,
    private val jobLauncher: JobLauncher,
) {

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES, zone = TIME_ZONE)
    fun run() {
        val clock = Clock.system(ZoneId.of(TIME_ZONE))
        val jobParameters = JobParameters(
            mapOf("requestTime" to JobParameter(Date.from(Instant.now(clock))))
        )
        jobLauncher.run(job, jobParameters)
    }

    companion object {
        const val TIME_ZONE = "Asia/Seoul"
    }
}