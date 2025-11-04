package com.chapter.android.nav3.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.presentation.detail.DetailScreen
import com.chapter.android.nav3.presentation.list.ListScreen
import com.chapter.android.nav3.presentation.splash.SplashScreen


@Composable
fun NavGraph() {
    val test = "ChapterNavigation3"
    val backStack = remember { mutableStateListOf<Any>(SplashPokemonDestination) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<SplashPokemonDestination> { //meter metadatos
                SplashScreen(
                    goToPokemonList = { backStack.add(ListPokemonDestination) }
                )
            }
            entry<ListPokemonDestination> {
                ListScreen(
                    onBack = { backStack.removeLastOrNull() },
                    onPokemonSelected = { pokemonDto ->
                        backStack.add(DetailPokemonDestination(pokemon = pokemonDto))
                    }
                )
            }
            entry<DetailPokemonDestination> { destination ->
                DetailScreen(
                    pokemon = destination.pokemon,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}