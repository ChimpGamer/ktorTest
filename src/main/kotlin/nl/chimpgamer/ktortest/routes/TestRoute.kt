package nl.chimpgamer.ktortest.routes

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.test() {
    get("/test") {
        call.respond("It works!")
    }
}