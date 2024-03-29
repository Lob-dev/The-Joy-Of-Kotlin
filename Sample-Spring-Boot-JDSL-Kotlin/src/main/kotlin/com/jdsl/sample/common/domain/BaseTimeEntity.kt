package com.jdsl.sample.common.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseTimeEntity(
        @CreatedDate
        @Column(nullable = false, updatable = false)
        val createAt: LocalDateTime = LocalDateTime.now(),

        @LastModifiedDate
        @Column(nullable = false)
        val updateAt: LocalDateTime = LocalDateTime.now(),
) {
}