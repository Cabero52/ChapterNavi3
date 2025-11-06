package com.chapter.android.nav3.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chapter.android.nav3.data.datasource.PokemonRemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(
    val favorites: Map<String, Boolean>,
    val pokemonDataSource: PokemonRemoteDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(ListState())
    val state: StateFlow<ListState> = _state

    fun onAction(action: ListAction) {
        when (action) {
            ListAction.LoadData -> loadData()
            ListAction.OnBack -> TODO()
            ListAction.OnDialogDismiss -> dismissDialog()
            ListAction.OnRetry -> TODO()
            else -> Unit
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val result = pokemonDataSource.getPage(100, 0)
            val updated = result.results.map {
                it.copy(isFavorite = favorites[it.name] ?: false)
            }
            _state.update { it.copy(items = updated) }
        }
    }

    private fun handleErrors(error: Exception) {
        _state.update { state ->
            state.copy(
                isLoading = false,
            )
        }

    }

    private fun dismissDialog() {
        _state.update {
            it.copy(
                dialog = ListState.Dialog.NoDialog
            )
        }
    }
}
