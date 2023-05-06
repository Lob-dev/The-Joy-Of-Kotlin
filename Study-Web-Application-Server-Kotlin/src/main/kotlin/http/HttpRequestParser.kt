package http

import mu.KLogger
import mu.KotlinLogging
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class HttpRequestParser(inputStream: InputStream) {
    private val logger: KLogger = KotlinLogging.logger { }
    val requestLine: String
    val requestHeaders: MutableList<String> = mutableListOf()
    val requestBody: String?

    init {
        val reader = BufferedReader(InputStreamReader(inputStream))
        requestLine = reader.readLine()
        logger.debug { "requestLine = $requestLine" }

        var readLine: String
        while (reader.readLine().also { readLine = it } != null && readLine.isNotEmpty()) {
            requestHeaders.add(readLine)
        }
        logger.debug { "requestHeaders = $requestHeaders" }

        val requestBodyBuilder = StringBuilder()
        while (reader.ready()) {
            requestBodyBuilder.append(reader.readLine())
        }
        requestBody = requestBodyBuilder.toString()
        logger.debug { "requestBody = $requestBody" }
    }
}
