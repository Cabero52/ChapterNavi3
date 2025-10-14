package com.chapter.android.nav3.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Paginated<PokemonDto>(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonDto>
)

@Serializable
data class PokemonDto(
    val id: Int,
    val name: String,
    val height: Int? = null,
    val weight: Int? = null,
    val sprites: Sprites? = null,
)
@Serializable
data class Sprites(
    val front_default: String? = null
)