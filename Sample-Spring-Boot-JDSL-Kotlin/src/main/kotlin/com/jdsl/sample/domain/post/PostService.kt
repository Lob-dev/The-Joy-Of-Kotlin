package com.jdsl.sample.domain.post

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class PostService(
        private val postRepository: PostRepository,
) {

    fun write(newPost: Post): Post = postRepository.save(newPost)

    @Transactional(readOnly = true)
    fun getPage(pageable: Pageable): Page<Post> = postRepository.getPage(pageable)

    @Transactional
    fun update(modifyPost: Post): Post = postRepository.findByIdOrNull(modifyPost.id).apply {
        modifyPost.title = modifyPost.title
        modifyPost.content = modifyPost.content
    } ?: throw EntityNotFoundException("Entity Not Found Exception.")

    fun delete(targetPostId: Long): Unit = postRepository.deleteById(targetPostId)
}