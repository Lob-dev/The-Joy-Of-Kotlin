package com.login.social.sample.account.domain.persistence

import org.springframework.security.oauth2.core.user.OAuth2User
import javax.persistence.*

@Entity
class AccountEntity(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true, length = 30)
    var username: String,

    @Column(nullable = false, unique = true, length = 50)
    var email: String,

    @Column(nullable = false, length = 20)
    var password: String,
) {
    companion object {
        fun toEntity(user: OAuth2User) = AccountEntity(
            username = (user.attributes["profile_nickname"] ?: user.name) as String,
            email = (user.attributes["account_email"] ?: "") as String,
            password = ""
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AccountEntity
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "AccountEntity(id=$id, username='$username', email='$email', password='$password')"
    }
}