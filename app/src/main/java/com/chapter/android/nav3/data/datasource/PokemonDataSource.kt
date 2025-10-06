package com.chapter.android.nav3.data.datasource

import io.ktor.client.HttpClient

class PokemonRemoteDataSource(private val api: PokemonApi) {
    suspend fun getPage(limit: Int, offset: Int): Paginated<NamedResource> =
        api.listPokemon(limit, offset)
    suspend fun getDetail(idOrName: String): PokemonDto =
        api.getPokemon(idOrName)
}