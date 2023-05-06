import http.SimpleWebApplicationServer

class SimpleWebApplicationServerApplication

fun main() {
    val server = SimpleWebApplicationServer(8080, 16)
    server.start()
}
