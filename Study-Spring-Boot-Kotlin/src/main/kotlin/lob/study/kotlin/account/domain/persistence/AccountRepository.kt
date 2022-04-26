package lob.study.kotlin.account.domain.persistence

import org.springframework.data.jpa.repository.JpaRepository


interface AccountRepository : JpaRepository<AccountEntity, Long>
