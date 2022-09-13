package com.jdsl.sample.domain.account

import com.jdsl.sample.common.domain.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "Account")
class Account(
        val name: String,
        val age: Int,
        val email: String,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
) : BaseTimeEntity() {
}