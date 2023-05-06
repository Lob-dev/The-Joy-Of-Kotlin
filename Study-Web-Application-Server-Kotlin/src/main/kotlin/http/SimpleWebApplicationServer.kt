package http

import mu.KLogger
import mu.KotlinLogging
import java.net.InetAddress
import java.net.ServerSocket
import java.util.concurrent.Executors


class SimpleWebApplicationServer(
    private val port: Int = 8080,
    private val backlog: Int = 16,
    private val address: InetAddress = InetAddress.getLocalHost(),
) {
    private val logger: KLogger = KotlinLogging.logger { }

    // TODO: 2023-05-06 Handler 초기화 로직 추가
    fun start() {
        val threadPool = Executors.newFixedThreadPool(backlog)
        ServerSocket(port, backlog, address).use { serverSocket ->
            logger.info { "Web Server Started! listen port = $port" }
            while (true) {
                val clientSocket = serverSocket.accept()
                threadPool.execute(HttpRequestHandler(clientSocket))
            }
        }
    }
}
