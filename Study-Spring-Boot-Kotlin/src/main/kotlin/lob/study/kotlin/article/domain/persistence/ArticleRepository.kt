package lob.study.kotlin.article.domain.persistence

import org.springframework.data.jpa.repository.JpaRepository


interface ArticleRepository : JpaRepository<ArticleEntity, Long>
