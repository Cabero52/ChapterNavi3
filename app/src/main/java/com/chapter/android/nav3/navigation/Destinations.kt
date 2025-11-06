package com.chapter.android.nav3.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object SplashPokemonDestination : NavKey
@Serializable
data object ListPokemonDestination : NavKey
@Serializable
data class DetailPokemonDestination(val id: Int) : NavKey
