package com.jdsl.sample.domain.account

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class AccountService(
        private val accountRepository: AccountRepository,
) {

    fun join(newAccount: Account): Account = accountRepository.save(newAccount)

    fun findAll(): List<Account> = accountRepository.findAll()

    fun findById(accountId: Long): Account = accountRepository.findByIdOrNull(accountId)
            ?: throw EntityNotFoundException("Entity Not Found Exception.")
}