package com.sample.batch.jooq

interface IdentityAware<T> {
    fun getIdentity(): T
}