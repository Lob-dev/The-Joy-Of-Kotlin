package http

import template.*
import mu.KLogger
import mu.KotlinLogging
import java.io.BufferedOutputStream
import java.io.OutputStreamWriter
import java.net.Socket
import java.util.*

class HttpRequestHandler(
    private val clientSocket: Socket,
) : Runnable {
    private val logger: KLogger = KotlinLogging.logger { }

    override fun run() {
        logger.debug { "clientSocket = $clientSocket" }
        try {
            val outputStream = clientSocket.getOutputStream()
            clientSocket.getInputStream().use { inputStream ->
                val requestParser = HttpRequestParser(inputStream)

                // TODO: 2023-05-06 Handler 라우팅 로직 추가
                    // val mappingHandlers = mutableListOf<String>()
                    // val mappingHandler = mappingHandlers.find(requestParser.requestLine)
                    // mappingHandler.execute(requestParser.requestBody)

                // TODO: 2023-05-06 등록된 Handler에 따라 응답이 변경되도록 수정
                OutputStreamWriter(BufferedOutputStream(outputStream)).use { writer ->
                    // TODO: 2023-05-06 Handler가 응답한 view 이름을 참조하도록 수정
                    val model = mapOf("value" to "http://github.com/lob-dev")
                    var htmlTemplate = HtmlTemplate()
                        .head {
                            add(H1().apply { add(P().apply { add("Welcome to my website!") }) })
                            add(A().apply {
                                add("{{value}}",
                                    "my profile link = {{value}}")
                            })
                        }.body {
                            add(P().apply { add("Thanks for visiting!") })
                        }
                        .toString()

                    writer.write(HtmlTemplateEvaluator(html = htmlTemplate, model).render())
                    writer.write("HTTP/1.1 200 OK")
                    writer.write("Date: ${{ Date() }}\r\n")
                    writer.write("Content-length: ${htmlTemplate.length}\r\n")
                    writer.write("Content-type: text/html\r\n")
                    writer.write("\r\n")
                    writer.flush()
                }
            }
        } catch (exception: Exception) {
            logger.info { "http.HttpRequestHandler.run() throw exception = ${exception.localizedMessage}" }
        } finally {
            clientSocket.close()
        }
    }
}
