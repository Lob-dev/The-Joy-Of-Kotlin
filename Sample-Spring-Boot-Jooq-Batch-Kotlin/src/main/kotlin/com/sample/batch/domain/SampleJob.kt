package com.sample.batch.domain

import com.sample.batch.jooq.IdentityAware
import java.time.LocalDateTime

class SampleJob(
    var isSuccess: Boolean,
    val createDate: LocalDateTime,
    val updateDate: LocalDateTime,
    val id: Long?,
) : IdentityAware<Long> {
    override fun getIdentity(): Long = id!!
}