package nl.chimpgamer.ktortest

import io.ktor.server.engine.*
import io.ktor.server.jetty.*
import io.ktor.server.routing.*
import nl.chimpgamer.ktortest.routes.test


private lateinit var env: ApplicationEngineEnvironment
private lateinit var webServer: JettyApplicationEngine

fun main(args: Array<String>) {
    env = applicationEngineEnvironment {
        module {
            routing {
                test()
            }
        }
        connector {
            port = 777
        }
    }

    webServer = embeddedServer(Jetty, env).apply {
        start(wait = false)
    }
}