package com.chapter.android.nav3.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chapter.android.nav3.data.datasource.PokemonRemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val id: Int,
    private val pokemonDataSource: PokemonRemoteDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state

    init {
        viewModelScope.launch {
            val result = pokemonDataSource.getDetail("$id")
            _state.update { it.copy(view = DetailState.View.Loaded(result), name = result.name) }
        }
    }

    fun onAction(action: DetailActions) {
        when (action) {
            DetailActions.OnBack -> TODO()
            DetailActions.OnDialogDismiss -> dismissDialog()
            DetailActions.OnRetry -> TODO()
            DetailActions.OnFavorite -> _state.update { it.copy(isFavorite = !it.isFavorite) }
            else -> Unit
        }
    }

    private fun dismissDialog() {
        _state.update {
            it.copy(
                dialog = DetailState.Dialog.NoDialog
            )
        }
    }
}
