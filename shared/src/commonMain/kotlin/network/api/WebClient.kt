package network.api

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object WebClient {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(
                contentType = ContentType.Application.Json,
                json = Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = true
                })
        }
    }
}