package com.lob.kotlin.demo.syntax

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.ANNOTATION_CLASS
)
@Retention(AnnotationRetention.RUNTIME)
annotation class Marker(
    val message: String = ""
)

