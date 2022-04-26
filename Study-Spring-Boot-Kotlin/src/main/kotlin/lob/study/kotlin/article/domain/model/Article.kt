package lob.study.kotlin.article.domain.model

import lob.study.kotlin.article.domain.persistence.ArticleEntity
import java.time.LocalDateTime
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.streams.toList


data class Article(
    var id: Long? = null,

    @get:Size(max = 255)
    var title: String,

    @get:Size(max = 1000)
    var content: String,

    @get:NotNull
    var accountId: Long? = null,

    var createdAt: LocalDateTime? = null,

    var updatedAt: LocalDateTime? = null
) {
    companion object {

        @JvmStatic
        fun of(articles: List<ArticleEntity>) = articles.stream()
            .map { article -> of(article) }
            .toList()

        @JvmStatic
        fun of(article: ArticleEntity) = Article(
            id = article.id,
            title = article.title,
            content = article.content,
            accountId = article.account.id,
            createdAt = article.createdAt,
            updatedAt = article.updatedAt
        )
    }

    fun toEntity() = ArticleEntity(id = id, title = title, content = content)
}