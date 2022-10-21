package com.sample.batch.jooq

import mu.KLogger
import mu.KotlinLogging
import org.jooq.ExecuteContext
import org.jooq.impl.DefaultExecuteListener
import org.jooq.tools.StopWatch

class SlowQueryListener : DefaultExecuteListener() {
    private val logger: KLogger = KotlinLogging.logger { }
    private lateinit var watch: StopWatch

    override fun executeStart(ctx: ExecuteContext) {
        super.executeStart(ctx)
        logger.debug { "Execute Node Url = ${ctx.connection().metaData.url}" }
        watch = StopWatch()
    }

    override fun executeEnd(ctx: ExecuteContext) {
        super.executeEnd(ctx)
        if (watch.split() > ONE_SEC_TO_NANO) {
            logger.info { "${ctx.connection().metaData.url} Slow Query Detected! ${ctx.query()}" }
        }
    }

    companion object {
        const val ONE_SEC_TO_NANO: Long = 1000000000L
    }
}

