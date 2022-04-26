package lob.study.kotlin.global.persistence

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

//@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
class BaseTimeEntity {

    //@CreateDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null

    //@LastModifiedDate
    @Column(nullable = false)
    var updatedAt: LocalDateTime? = null

    @PrePersist
    fun prePersist() {
        createdAt = LocalDateTime.now()
        updatedAt = createdAt
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }
}