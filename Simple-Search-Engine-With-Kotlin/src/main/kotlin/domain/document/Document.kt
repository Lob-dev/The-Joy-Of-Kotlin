
package domain.document

import interfaces.BlogPost

data class Document(val id: Long, val title: String, val content: String) {

    companion object {
        fun from(blogPost: BlogPost) = Document(blogPost.id.toLong(), blogPost.title, blogPost.content)
    }
}
