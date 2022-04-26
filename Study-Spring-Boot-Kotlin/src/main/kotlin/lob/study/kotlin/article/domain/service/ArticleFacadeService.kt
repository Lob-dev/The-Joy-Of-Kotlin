package lob.study.kotlin.article.domain.service

import lob.study.kotlin.account.domain.persistence.AccountEntity
import lob.study.kotlin.account.domain.service.AccountService
import lob.study.kotlin.article.domain.model.Article
import lob.study.kotlin.article.domain.persistence.ArticleEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ArticleFacadeService(
    private val articleService: ArticleService,
    private val accountService: AccountService
) {

    @Transactional
    fun writeArticle(article: Article): Long {
        val savedArticle: ArticleEntity = articleService.create(article)
        val targetAccount: AccountEntity = accountService.getOne(article.accountId!!)
        savedArticle.mappingAccount(targetAccount)
        return savedArticle.id!!
    }
}