package network.api

import dataClass.Type
import io.ktor.client.call.*
import io.ktor.client.request.*

object PokemonTypeAPI {
    suspend fun getAllPokemonType(): List<Type> {
        return WebClient.client.get("https://pokebuildapi.fr/api/v1/types").body()
    }
}