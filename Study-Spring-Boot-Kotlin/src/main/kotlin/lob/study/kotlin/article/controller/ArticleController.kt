package lob.study.kotlin.article.controller

import lob.study.kotlin.article.domain.model.Article
import lob.study.kotlin.article.domain.service.ArticleFacadeService
import lob.study.kotlin.article.domain.service.ArticleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Positive


@Validated
@RestController
@RequestMapping(value = ["/api/posts"])
class ArticleController(
    private val articleService: ArticleService,
    private val articleFacadeService: ArticleFacadeService
) {

    @GetMapping
    fun gets(): ResponseEntity<List<Article>> = ResponseEntity.ok(Article.of(articleService.findAll()))

    @GetMapping("/{id}")
    fun getOne(@Positive @PathVariable id: Long): ResponseEntity<Article> = ResponseEntity.ok(Article.of(articleService.getOne(id)))

    @PostMapping
    fun write(@Valid @RequestBody article: Article): ResponseEntity<Long> = ResponseEntity.status(HttpStatus.CREATED).body(articleFacadeService.writeArticle(article))

    @PutMapping("/{id}")
    fun update(@Positive @PathVariable id: Long, @Valid @RequestBody article: Article): ResponseEntity<Void> {
        articleService.update(id, article)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@Positive @PathVariable id: Long): ResponseEntity<Void> {
        articleService.delete(id)
        return ResponseEntity.noContent().build()
    }
}