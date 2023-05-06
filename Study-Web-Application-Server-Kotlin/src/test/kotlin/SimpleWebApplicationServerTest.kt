import org.junit.jupiter.api.Test
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

/**
 * @see Http1HeaderParser
 * @Refer https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.net.http/share/classes/jdk/internal/net/http/Http1HeaderParser.java
 *
 * @see Response Header
 * @Refer https://developer.mozilla.org/en-US/docs/Glossary/Response_header
 */
class SimpleWebApplicationServerTest {

    @Test
    fun responseMustBeParsed() {
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("http://172.23.224.1:8080"))
            .header("Content-Type", "text/plain")
            .build()
        client.send(request, HttpResponse.BodyHandlers.ofString())
    }
}
