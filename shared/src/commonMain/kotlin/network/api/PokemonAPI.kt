package network.api

import dataClass.Pokemon
import io.ktor.client.call.*
import io.ktor.client.request.*

object PokemonAPI {

    suspend fun getAllPokemons(): List<Pokemon> {
        return WebClient.client.get("https://pokebuildapi.fr/api/v1/pokemon").body()
    }

}