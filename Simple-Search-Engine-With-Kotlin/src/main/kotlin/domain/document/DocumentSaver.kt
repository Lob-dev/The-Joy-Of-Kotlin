package domain.document

import java.util.logging.Logger

class DocumentSaver(
    private val documentRepository: DocumentRepository,
) {
    val logger: Logger = Logger.getLogger("DocumentSaver.kt")

    fun writeDocuments(documents: List<Document>) {
        documents.forEach { documentRepository.persist(it) }
    }
}
