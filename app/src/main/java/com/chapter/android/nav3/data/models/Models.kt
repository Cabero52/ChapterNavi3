package com.chapter.android.nav3.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Paginated(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonDto>
)

@Serializable
data class PokemonDto(
    val name: String,
    val url: String
)

@Serializable
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonTypeResponse>,
) {
    val image: String
        inline get() {
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        }
}

@Serializable
data class PokemonTypeResponse(
    val slot: Int,
    val type: TypeResponse
)

@Serializable
data class TypeResponse(
    val name: String,
    val url: String
)