package domain.document

import EMPTY
import PUNCTUATION_REGEX
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

val LOGGER: Logger = Logger.getLogger("DocumentRepository.kt")
val DOCUMENT_TABLE: SortedMap<Long, Document> = TreeMap()


// Inverted Index
    // https://medium.com/@lunay0ung/basics-%EC%9D%B8%EB%8D%B1%EC%8A%A4%EC%99%80-%EC%97%AD%EC%9D%B8%EB%8D%B1%EC%8A%A4-inverted-index-a95573836189
    // https://velog.io/@ayoung0073/Elasticsearch-%EC%9E%85%EB%AC%B8-%EA%B3%B5%EB%B6%80
val INVERTED_INDEX_MAP: SortedMap<String, MutableSet<Long>> = TreeMap()

class DocumentRepository(
    private val documentTable: SortedMap<Long, Document> = TreeMap(),
    private val invertedIndexMap: SortedMap<String, MutableSet<Long>> = TreeMap()
) {

    fun persist(document: Document) {
        documentTable[document.id] = document
        indexing(document)
    }

    fun hasAssociatesDocument(query: String): Boolean = invertedIndexMap.containsKey(query)

    fun findAllIdsByQuery(query: String): MutableSet<Long>? = invertedIndexMap[query]

    fun findAllIdsIn(ids: List<Long>?): MutableList<Document> {
        val associateDocuments: MutableList<Document> = mutableListOf()
        ids?.forEach { documentId ->
            val document = documentTable[documentId]
            if (document != null) {
                associateDocuments.add(document)
            }
        }
        return associateDocuments
    }

    private fun indexing(document: Document) {
        // 1. 지정된 쿼리에 대해 컬렉션의 모든 문서에 대한 점수를 계산한다.
        // 각 문서가 query 얼마나 관련이 있는지 확인하려면 겹치는 용어의 수를 세는 것이 간단하다.
        val tokenizer = StringTokenizer(document.content.replace(PUNCTUATION_REGEX, EMPTY))
        val tokensByDocument = mutableListOf<String>()
        while (tokenizer.hasMoreTokens()) {
            tokensByDocument.add(tokenizer.nextToken())
        }

        LOGGER.log(Level.INFO, "tokenizedDocument = $tokensByDocument")

        // 형태소 분석 필요 (추후 적용) implementation 'org.apache.lucene:lucene-core:9.7.0'
        // org.tartarus.snowball.ext.PorterStemmer
        // https://lucene.apache.org/

        // Token을 사전순으로 정렬하고 document id 매핑
        tokensByDocument.forEach { term ->
            val documentIds = mutableSetOf<Long>()
            documentIds.add(document.id)

            if (invertedIndexMap.containsKey(term).not()) {
                invertedIndexMap[term] = documentIds
            } else {
                invertedIndexMap[term]?.addAll(documentIds)
            }
        }

        LOGGER.log(Level.INFO, "invertedIndexMap = $invertedIndexMap")
    }
}
