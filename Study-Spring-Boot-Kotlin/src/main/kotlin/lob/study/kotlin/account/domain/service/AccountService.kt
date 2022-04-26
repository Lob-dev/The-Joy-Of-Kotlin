package lob.study.kotlin.account.domain.service

import lob.study.kotlin.account.domain.model.Account
import lob.study.kotlin.account.domain.persistence.AccountEntity
import lob.study.kotlin.account.domain.persistence.AccountRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException


@Service
class AccountService(
    private val accountRepository: AccountRepository
) {

    @Transactional(readOnly = true)
    fun findAll(): List<AccountEntity> = accountRepository.findAll()

    @Transactional(readOnly = true)
    fun getOne(id: Long): AccountEntity = accountRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    fun create(account: Account): Long = accountRepository.save(account.toEntity()).id!!

    @Transactional
    fun update(id: Long, account: Account) {
        val targetAccount: AccountEntity = getOne(id)
        targetAccount.updateInfo(account)
    }

    fun delete(id: Long) = accountRepository.delete(getOne(id))
}
