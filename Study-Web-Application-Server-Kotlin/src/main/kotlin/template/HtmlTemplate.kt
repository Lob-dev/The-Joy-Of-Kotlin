package template

import java.util.*

class HtmlTemplate {
    private val elements = mutableListOf<Element>()

    fun head(block: HEAD.() -> Unit): HtmlTemplate {
        val head = HEAD()
        head.block()
        elements.add(head)
        return this
    }

    fun body(block: BODY.() -> Unit): HtmlTemplate {
        val body = BODY()
        body.block()
        elements.add(body)
        return this
    }

    override fun toString(): String {
        val html = StringBuilder("<!DOCTYPE html>\n<html>\n")
        elements.forEach {
            html.append(it.toString())
        }
        html.append("</html>")
        return html.toString()
    }
}

abstract class Element {
    private val children = mutableListOf<Element>()

    fun add(element: Element) {
        children.add(element)
    }

    override fun toString(): String {
        val tag = javaClass.simpleName.lowercase(Locale.getDefault())
        return "<$tag>${children.joinToString("")}</$tag>"
    }
}

class HEAD : Element()
class BODY : Element()
class H1 : Element()
class H2 : Element()
open class P : Element() {
    protected var paragraph = ""

    fun add(paragraph: String) {
        this.paragraph = paragraph
    }

    override fun toString(): String {
        val tag = javaClass.simpleName.lowercase(Locale.getDefault())
        return "<$tag>${paragraph}</$tag>"
    }
}

class A : P() {
    private var link = ""

    fun add(link: String, paragraph: String) {
        this.link = link
        this.paragraph = paragraph
    }

    override fun toString(): String {
        val tag = javaClass.simpleName.lowercase(Locale.getDefault())
        return "<$tag href=${link}>${paragraph}</$tag>"
    }
}
