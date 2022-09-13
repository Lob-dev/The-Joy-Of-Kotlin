package com.jdsl.sample.domain.post

import com.jdsl.sample.common.domain.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "Post")
class Post(
        var title: String,
        var content: String,
        val authorId: Long,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
) : BaseTimeEntity() {
}