package com.chapter.android.nav3.data.datasource

import com.chapter.android.nav3.data.models.Paginated
import com.chapter.android.nav3.data.models.PokemonDetailResponse
import com.chapter.android.nav3.data.network.PokemonApi

class PokemonRemoteDataSource(private val api: PokemonApi) {
    suspend fun getPage(limit: Int, offset: Int): Paginated =
        api.listPokemon(limit, offset)

    suspend fun getDetail(idOrName: String): PokemonDetailResponse =
        api.getPokemon(idOrName)
}