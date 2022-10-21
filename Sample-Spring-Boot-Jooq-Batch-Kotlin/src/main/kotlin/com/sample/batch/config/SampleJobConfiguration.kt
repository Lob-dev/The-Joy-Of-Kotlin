package com.sample.batch.config

import com.sample.batch.domain.SampleJob
import com.sample.batch.jooq.JooqPagingNoOffsetItemReader
import jooq.dsl.tables.JSampleJob
import jooq.dsl.tables.records.JSampleJobRecord
import mu.KLogger
import mu.KotlinLogging
import org.jooq.Condition
import org.jooq.DSLContext
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class SampleJobConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val query: DSLContext,
) {
    private val logger: KLogger = KotlinLogging.logger { }

    @Bean
    protected fun sampleJob(sampleJobStep: Step): Job = jobBuilderFactory.get("sampleJob")
        .start(sampleJobStep)
        .build()

    @JobScope
    @Bean
    protected fun sampleJobStep(
        itemReader: ItemReader<SampleJob>,
        itemProcessor: ItemProcessor<SampleJob, SampleJob>,
        itemWriter: ItemWriter<SampleJob>,
    ): Step = stepBuilderFactory.get("sampleJobStep").chunk<SampleJob, SampleJob>(50)
        .reader(itemReader)
        .processor(itemProcessor)
        .writer(itemWriter)
        .build()

    @StepScope
    @Bean
    protected fun itemReader(): ItemReader<SampleJob> {
        val condition: Condition = RECORD.CREATE_DATE.lessThan(LocalDateTime.now())
            .and(RECORD.IS_SUCCESS.isFalse)

        return JooqPagingNoOffsetItemReader<JSampleJobRecord, SampleJob>(
            query,
            RECORD,
            RETURN_TYPE,
            RECORD.ID,
            condition
        ).apply { pageSize = 50 }
    }

    @StepScope
    @Bean
    protected fun itemProcessor(): ItemProcessor<SampleJob, SampleJob> =
        ItemProcessor<SampleJob, SampleJob> {
            it.isSuccess = true
            logger.info { "sampleJob id = ${it.id} is success. ${it.isSuccess}" }
            it
        }

    @StepScope
    @Bean
    protected fun itemWriter(): ItemWriter<SampleJob> =
        ItemWriter {
            query.batched { config ->
                it.forEach { item ->
                    config.dsl().update(RECORD)
                        .set(RECORD.IS_SUCCESS, item.isSuccess)
                        .where(RECORD.ID.eq(item.getIdentity()))
                        .execute()
                }
            }
        }

    companion object {
        val RECORD: JSampleJob = JSampleJob.SAMPLE_JOB
        val RETURN_TYPE = SampleJob::class
    }
}