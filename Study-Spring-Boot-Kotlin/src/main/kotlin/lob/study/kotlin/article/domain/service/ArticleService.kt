package lob.study.kotlin.article.domain.service

import lob.study.kotlin.account.domain.service.AccountService
import lob.study.kotlin.article.domain.model.Article
import lob.study.kotlin.article.domain.persistence.ArticleEntity
import lob.study.kotlin.article.domain.persistence.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException


@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) {

    @Transactional(readOnly = true)
    fun findAll(): List<ArticleEntity> = articleRepository.findAll()

    @Transactional(readOnly = true)
    fun getOne(id: Long): ArticleEntity = articleRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    fun create(article: Article): ArticleEntity = articleRepository.save(article.toEntity())

    @Transactional
    fun update(id: Long, article: Article) {
        val targetArticle: ArticleEntity = getOne(id)
        targetArticle.updateContent(article)
    }

    @Transactional
    fun delete(id: Long) = articleRepository.delete(getOne(id))
}
