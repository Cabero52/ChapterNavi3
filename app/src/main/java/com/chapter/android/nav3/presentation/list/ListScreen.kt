package com.chapter.android.nav3.presentation.list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.chapter.android.nav3.data.models.PokemonDto

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
    ListContentScreen(
        state=state,
        onAction = {}
    )
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
    LazyColumn {
        items(items= state.items){
            PokeCard(
                item = it
            )
        }
    }
}

@Composable
fun PokeCard(
    item: PokemonDto,
){
    Text(item.name)
}
