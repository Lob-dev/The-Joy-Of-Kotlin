package com.jdsl.sample.interfaces.post

import com.jdsl.sample.domain.post.Post
import com.jdsl.sample.domain.post.PostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min

@Validated
@RestController
@RequestMapping("/api/posts")
class PostController(
        private val postService: PostService,
) {

    @PostMapping
    fun write(@RequestBody @Valid postWriteRequest: PostWriteRequest): ResponseEntity<Post> =
            ResponseEntity.ok(postService.write(postWriteRequest.toEntity()))

    @GetMapping
    fun getPage(@PageableDefault pageable: Pageable): ResponseEntity<Page<Post>> =
            ResponseEntity.ok(postService.getPage(pageable))

    @PutMapping("/{postId}")
    fun update(@PathVariable @Min(1) postId: Long, @RequestBody @Valid postUpdateRequest: PostUpdateRequest): ResponseEntity<Post> =
            ResponseEntity.ok(postService.update(postUpdateRequest.toEntityBy(postId)))

    @DeleteMapping("/{postId}")
    fun delete(@PathVariable @Min(1) postId: Long) = postService.delete(postId)
}