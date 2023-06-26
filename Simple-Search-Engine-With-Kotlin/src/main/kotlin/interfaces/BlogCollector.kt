package interfaces

import MOCK_CONTENTS
import kotlinx.serialization.json.Json
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger


class BlogCollector(
    private val httpClient: HttpClient = HttpClient.newHttpClient(),
) {
    val logger: Logger = Logger.getLogger("BlogCollector.kt")

    fun collectBlogPosts(testCondition: Boolean = true): List<BlogPost> {
        return if (testCondition) {
            (1..10).map {
                val contentSize = MOCK_CONTENTS.size
                BlogPost(id = "$it", "blog title sample $it", MOCK_CONTENTS[it % contentSize])
            }
        } else {
            val httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.mocksite.com/posts"))
                .build()

            val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
            if (httpResponse.statusCode() == 200) {
                val responseBody = httpResponse.body()
                Json.decodeFromString(responseBody)
            } else {
                logger.log(Level.INFO, "${httpResponse.statusCode()} ${httpResponse.body()}")
                Collections.emptyList()
            }
        }
    }
}
