package com.chapter.android.nav3.presentation.splash

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onBack: () -> Unit = {},
) {
    val state by viewModel.state.collectAsState()

    // Lógica de acciones
    val onAction by remember {
        mutableStateOf<(SplashAction) -> Unit>({ action ->
            when (action) {
                SplashAction.OnBack -> onBack()
                else -> viewModel.onAction(action)
            }
        })
    }

    BackHandler {
        onAction(SplashAction.OnBack)
    }

    if (state.isLoading) {

    }

    when (state.dialog) {
        SplashState.Dialog.NoConnected -> {

        }

        SplashState.Dialog.General -> {

        }

        else -> Unit
    }
}

@Composable
fun SplashContentScreen(
    modifier: Modifier = Modifier,
    state: SplashState,
    onAction: (SplashAction) -> Unit
) {


}
