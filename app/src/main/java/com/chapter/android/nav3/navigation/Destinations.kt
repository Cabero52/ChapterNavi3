package com.chapter.android.nav3.navigation

import androidx.navigation3.runtime.NavKey
import com.chapter.android.nav3.data.models.PokemonDetailResponse
import com.chapter.android.nav3.data.models.PokemonDto
import kotlinx.serialization.Serializable

data object SplashPokemonDestination
data object ListPokemonDestination

@Serializable
data class DetailPokemonDestination(val pokemon: PokemonDetailResponse): NavKey
