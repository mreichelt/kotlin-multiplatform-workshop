package com.example

import com.example.habits.api.HabitsResponse
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json()
        }

        routing {
            get("/") {
                call.respondText("Hello World!")
            }
            get("/habits") {
                call.respond(HabitsResponse(habits = sampleHabits))
            }
        }
    }.start(wait = true)
}
