package domain.document

import java.util.logging.Level
import java.util.logging.Logger

class DocumentRetriever(
    private val documentRepository: DocumentRepository,
) {
    val logger: Logger = Logger.getLogger("DocumentReader.kt")

    // 추후 query -> inverted index (해당 하는 문서 탐색)
        // (해당하는 Query를 문서 내에 제일 많이 포함할 때) 산정된 점수에 따라 문서를 정렬하여 응답한다.
        // 점수가 높은 순으로 N개의 문서를 응답한다.
    fun findAllByQueryOrderByScoreLimitedTo(query: String, limitCount: Int = 10): List<Document> {
        val associateDocuments: MutableList<Document> = if (documentRepository.hasAssociatesDocument(query)) {
            val documentIds = documentRepository.findAllIdsByQuery(query)
            documentRepository.findAllIdsIn(documentIds?.toList())
        } else mutableListOf()

        val filteredAssociateDocuments = associateDocuments.filter { it.content.contains(query) }
        val sortedAssociateDocuments = filteredAssociateDocuments.sortedWith(
            compareByDescending<Document> { document -> document.content.split(query).count() - 1 }
                .thenComparing { document -> document.id }
        )

        logger.log(Level.INFO, "${filteredAssociateDocuments.map { it.id }}")
        return sortedAssociateDocuments.take(limitCount)
    }
}
