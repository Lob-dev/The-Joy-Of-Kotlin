package com.jdsl.sample.interfaces.account

import com.jdsl.sample.domain.account.Account
import com.jdsl.sample.domain.account.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min

@Validated
@RestController
@RequestMapping("/api/accounts")
class AccountController(
        private val accountService: AccountService,
) {

    @PostMapping
    fun join(@RequestBody @Valid accountJoinRequest: AccountJoinRequest) : ResponseEntity<Account> =
            ResponseEntity.ok(accountService.join(accountJoinRequest.toEntity()))

    @GetMapping
    fun findAll() : ResponseEntity<List<Account>> = ResponseEntity.ok(accountService.findAll())

    @GetMapping("/{accountId}")
    fun findByUserId(@PathVariable @Min(1) accountId: Long) =
            ResponseEntity.ok(accountService.findById(accountId))
}