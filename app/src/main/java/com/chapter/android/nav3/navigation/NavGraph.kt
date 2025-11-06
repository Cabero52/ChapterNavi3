package com.chapter.android.nav3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.presentation.list.ListScreen
import com.chapter.android.nav3.presentation.splash.SplashScreen

@Composable
fun NavGraph() {

    val backStack = remember { mutableStateListOf<Any>(SplashPokemonDestination) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<SplashPokemonDestination> {
                SplashScreen(
                    goToPokemonList = { backStack.add(ListPokemonDestination) }
                )
            }
            entry<ListPokemonDestination>{
                ListScreen(
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }

    )
}