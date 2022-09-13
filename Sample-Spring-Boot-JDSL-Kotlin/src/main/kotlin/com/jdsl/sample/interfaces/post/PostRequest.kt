package com.jdsl.sample.interfaces.post

import com.jdsl.sample.domain.post.Post

data class PostWriteRequest(
        val title: String,
        val content: String,
        val authorId: Long,
) {

    fun toEntity(): Post = Post(title, content, authorId)
}

data class PostUpdateRequest(
        val title: String,
        val content: String,
        val authorId: Long,
) {

    fun toEntityBy(postId: Long): Post = Post(title, content, authorId, postId)
}
