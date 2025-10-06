package com.chapter.android.nav3.presentation.list

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun ListScreen(
    viewModel: ListViewModel,
    onBack: () -> Unit = {},
) {
    val state by viewModel.state.collectAsState()

    // Lógica de acciones
    val onAction by remember {
        mutableStateOf<(ListAction) -> Unit>({ action ->
            when (action) {
                ListAction.OnBack -> onBack()
                else -> viewModel.onAction(action)
            }
        })
    }

    BackHandler {
        onAction(ListAction.OnBack)
    }

    if (state.isLoading) {

    }
    when (state.dialog) {
        ListState.Dialog.NoConnected -> {
        }

        ListState.Dialog.General -> {
        }

        else -> Unit
    }
}

@Composable
fun ListContentScreen(
    modifier: Modifier = Modifier,
    state: ListState,
    onAction: (ListAction) -> Unit
) {


}
