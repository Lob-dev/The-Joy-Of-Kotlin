import interfaces.BlogCollector
import domain.document.Document
import domain.document.DocumentRepository
import domain.document.DocumentSaver
import domain.document.DocumentRetriever
import java.util.logging.Level
import java.util.logging.Logger

fun main() {
    val documentRepository = DocumentRepository()
    val searchApplication = SampleSearchApplication(
        BlogCollector(),
        DocumentSaver(documentRepository),
        DocumentRetriever(documentRepository)
    )
    searchApplication.run("years")
}
