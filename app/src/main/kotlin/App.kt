package voting

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import voting.voting.web.plugins.configureMonitoring
import voting.voting.web.plugins.configureStatic
import voting.voting.web.plugins.configureTemplating

fun main() {
    embeddedServer(Netty, port = 4000, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureTemplating()
    configureMonitoring()
    configureStatic()
}
