package com.chapter.android.nav3.presentation.unauth.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.chapter.android.nav3.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    goToPokemonList: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(5000)
        goToPokemonList()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(
                id = R.drawable.international_pokemon_logo
            ),
            contentDescription = "POKEMON"
        )
    }
}
