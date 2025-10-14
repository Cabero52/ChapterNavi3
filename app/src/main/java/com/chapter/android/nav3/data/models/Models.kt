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