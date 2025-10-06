package com.chapter.android.nav3.presentation.detail

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    onBack: () -> Unit = {},
) {
    val state by viewModel.state.collectAsState()

    // Lógica de acciones
    val onAction by remember {
        mutableStateOf<(DetailActions) -> Unit>({ action ->
            when (action) {
                DetailActions.OnBack -> onBack()
                else -> viewModel.onAction(action)
            }
        })
    }

    BackHandler {
        onAction(DetailActions.OnBack)
    }

    if (state.isLoading) {

    }
    when (state.dialog) {
        DetailState.Dialog.NoConnected -> {

        }

        DetailState.Dialog.General -> {

        }

        else -> Unit
    }
}

@Composable
fun DetailContentScreen(
    modifier: Modifier = Modifier,
    state: DetailState,
    onAction: (DetailActions) -> Unit
) {


}
