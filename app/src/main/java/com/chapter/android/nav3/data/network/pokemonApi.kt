package com.chapter.android.nav3.data.network

import com.example.pokemon.data.remote.NetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.Serializable

interface PokemonApi {
    suspend fun listPokemon(limit: Int, offset: Int): Paginated<NamedResource>
    suspend fun getPokemon(idOrName: String): PokemonDto
}
class KtorPokemonApi(private val client: HttpClient) : PokemonApi {
    override suspend fun listPokemon(limit: Int, offset: Int): Paginated<NamedResource> =
        client.get("${NetworkConfig.API_PREFIX}pokemon") {
            parameter("limit", limit)
            parameter("offset", offset)
        }.body()
    override suspend fun getPokemon(idOrName: String): PokemonDto =
        client.get("${NetworkConfig.API_PREFIX}pokemon/$idOrName").body()
}