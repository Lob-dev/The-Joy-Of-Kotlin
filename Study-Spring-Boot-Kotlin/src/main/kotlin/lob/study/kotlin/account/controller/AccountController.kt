package lob.study.kotlin.account.controller

import lob.study.kotlin.account.domain.model.Account
import lob.study.kotlin.account.domain.service.AccountService
import java.lang.Void
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Positive


@Validated
@RestController
@RequestMapping(value = ["/api/users"])
class AccountController(
    private val accountService: AccountService
) {

    @GetMapping
    fun gets(): ResponseEntity<List<Account>> = ResponseEntity.ok(Account.of(accountService.findAll()))

    @GetMapping("/{id}")
    fun getOne(@Positive @PathVariable id: Long): ResponseEntity<Account> = ResponseEntity.ok(Account.of(accountService.getOne(id)))

    @PostMapping
    fun join(@RequestBody @Valid account: Account): ResponseEntity<Long> = ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(account))

    @PutMapping("/{id}")
    fun update(@Positive @PathVariable id: Long, @RequestBody @Valid account: Account): ResponseEntity<Void> {
        accountService.update(id, account)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@Positive @PathVariable id: Long): ResponseEntity<Void> {
        accountService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
