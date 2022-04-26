package lob.study.kotlin.account.domain.model

import lob.study.kotlin.account.domain.persistence.AccountEntity
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import kotlin.streams.toList


data class Account(
    var id: Long? = null,

    @get:NotBlank
    @get:Size(max = 15)
    var username: String,

    @get:NotBlank
    @get:Size(max = 50)
    var email: String,

    @get:NotBlank
    @get:Size(max = 20)
    var password: String,

    @get:NotBlank
    @get:Size(max = 10)
    var role: String,

    var createdAt: LocalDateTime?,

    var updatedAt: LocalDateTime?
) {
    companion object {

        // @JvmStatic, Field 의 const keyword 는 Java 에서 참조 가능하게 만든다.
        @JvmStatic
        fun of(accounts: List<AccountEntity>) = accounts.stream()
            .map { account -> of(account) }
            .toList()

        @JvmStatic
        fun of(account: AccountEntity) = Account(
            id = account.id,
            username = account.username,
            email = account.email,
            password = account.password,
            role = account.role,
            createdAt = account.createdAt,
            updatedAt = account.updatedAt
        )
    }

    fun toEntityWithRole(role: String) = AccountEntity(
        id = id,
        username = username,
        email = email,
        password = password,
        role = role
    )

    fun toEntity() = AccountEntity(id = id, username = username, email = email, password = password)
}