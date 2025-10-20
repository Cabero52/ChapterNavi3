package com.chapter.android.nav3.presentation.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    goToPokemonList: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(5000)
        goToPokemonList()
    }

    Text("NAVIGATION 3")
}
