package com.jdsl.sample.interfaces.account

import com.jdsl.sample.domain.account.Account
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class AccountJoinRequest(
        @get:NotBlank
        val name: String,
        @get:NotNull
        @get:Min(1)
        val age: Int,
        @get:Email
        val email: String,
) {

    fun toEntity(): Account = Account(name, age, email)
}