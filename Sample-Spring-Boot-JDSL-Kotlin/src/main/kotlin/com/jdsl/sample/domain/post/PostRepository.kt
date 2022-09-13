package com.jdsl.sample.domain.post

import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.pageQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface PostRepository : JpaRepository<Post, Long?>, PostQueryRepository {
}

interface PostQueryRepository {
    fun getPage(pageable: Pageable): Page<Post>
}

@Repository
class PostQueryRepositoryImpl(
        private val queryFactory: SpringDataQueryFactory,
) : PostQueryRepository {

    override fun getPage(pageable: Pageable): Page<Post> =
            queryFactory.pageQuery(pageable) {
                select(entity(Post::class))
                from(entity(Post::class))
            }
}