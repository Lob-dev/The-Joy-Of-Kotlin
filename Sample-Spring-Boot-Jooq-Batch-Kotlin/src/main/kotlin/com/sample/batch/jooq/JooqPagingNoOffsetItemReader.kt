package com.sample.batch.jooq

import org.jooq.*
import org.springframework.batch.item.database.AbstractPagingItemReader
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.reflect.KClass

class JooqPagingNoOffsetItemReader<R : UpdatableRecord<*>, T : IdentityAware<Long>>(
    private val query: DSLContext,
    private val record: Table<R>,
    private val type: KClass<T>,
    private val id: Field<Long>,
    private val condition: Condition,
) : AbstractPagingItemReader<T>() {
    private var currentOffset: Long = INITIAL_OFFSET

    override fun doReadPage() {
        initOrClearResults()
        fetchResults(currentOffset, condition)
        updateCurrentOffsetIfNotLastPage()
    }

    private fun initOrClearResults() {
        if (results == null || results.isEmpty()) {
            results = CopyOnWriteArrayList()
        } else {
            results.clear()
        }
    }

    private fun fetchResults(start: Long, condition: Condition) {
        results = findAllWithPaging(start, condition)
        logger.debug("results = $results")
    }

    private fun findAllWithPaging(offset: Long, condition: Condition): MutableList<T> =
        query.selectFrom(record)
            .where(id.greaterThan(offset).and(condition))
            .orderBy(id.asc())
            .limit(pageSize)
            .fetchInto(type.java)

    private fun updateCurrentOffsetIfNotLastPage() {
        if (results.isNotEmpty() && results[FIRST_ELEMENT_INDEX] != null) {
            currentOffset = results.last().getIdentity()
            logger.debug("currentOffset = $currentOffset")
        }
    }

    override fun doJumpToPage(itemIndex: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        const val FIRST_ELEMENT_INDEX = 0
        const val INITIAL_OFFSET = 0L
    }
}
