package com.jdsl.sample.domain.account

import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface AccountRepository : JpaRepository<Account, Long?>, AccountQueryRepository {
}

interface AccountQueryRepository {
}

@Repository
class AccountQueryRepositoryImpl(
        private val queryFactory: SpringDataQueryFactory,
) : AccountQueryRepository {
}