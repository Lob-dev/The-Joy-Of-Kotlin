import domain.document.Document
import domain.document.DocumentRetriever
import domain.document.DocumentSaver
import interfaces.BlogCollector
import java.util.logging.Level
import java.util.logging.Logger

class SampleSearchApplication(
    private val blogCollector: BlogCollector,
    private val documentSaver: DocumentSaver,
    private val documentRetriever: DocumentRetriever,
) {
    val logger: Logger = Logger.getLogger("SampleSearchApplication.kt")

    fun run(query: String) {
        val blogPosts = blogCollector.collectBlogPosts()
        documentSaver.writeDocuments(blogPosts.map { Document.from(it) })
        val documents = documentRetriever.findAllByQueryOrderByScoreLimitedTo(query, 5)
        logger.log(Level.INFO, "$documents")
    }
}
