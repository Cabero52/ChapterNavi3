package com.chapter.android.nav3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

@Composable
fun NavGraph() {

    val backStack = remember { mutableStateListOf<Any>(SplashPokemonDestination) }

    // Completa el NavDisplay, a ver si habéis estado atentos. 😉
    NavDisplay(
        backStack = backStack,
        onBack = { },
        entryProvider = entryProvider {}
    )
}