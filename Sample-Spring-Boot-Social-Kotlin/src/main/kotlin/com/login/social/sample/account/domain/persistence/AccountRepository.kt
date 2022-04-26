package com.login.social.sample.account.domain.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface AccountRepository : JpaRepository<AccountEntity, Long> {

    fun existsByUsernameLike(username: String) : Boolean
}