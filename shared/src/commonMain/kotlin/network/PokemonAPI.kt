package network

import dataClass.Pokedex
import dataClass.Pokemon
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PokemonAPI {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                contentType = ContentType.Application.Json,
                json = Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = true
                })
        }
    }
    suspend fun getAllPokemons(): List<Pokemon>{
        return httpClient.get("https://pokebuildapi.fr/api/v1/pokemon").body()
    }
    suspend fun getPokemonById(): Pokemon {
        return httpClient.get("https://pokebuildapi.fr/api/v1/pokemon/" + 1).body()
    }
}