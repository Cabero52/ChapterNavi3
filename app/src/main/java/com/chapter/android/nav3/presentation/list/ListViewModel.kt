package com.chapter.android.nav3.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chapter.android.nav3.data.datasource.PokemonRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(
    val pokemonDataSource: PokemonRemoteDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(ListState())
    val state: StateFlow<ListState> = _state

    init {
        viewModelScope.launch{
            val result = pokemonDataSource.getPage(100,0)
            _state.update { it.copy(items = result.results) }
        }
    }

    fun onAction(action: ListAction) {
        when (action) {
            ListAction.OnBack -> TODO()
            ListAction.OnDialogDismiss -> dismissDialog()
            ListAction.OnRetry -> TODO()
            ListAction.ResetPokemon -> resetPokemonInfo()
            is ListAction.GetPokemonDetail -> obtainPokemonData(action.pokemonName)
            else -> Unit
        }
    }


    private fun resetPokemonInfo(){
        _state.update { state ->
            state.copy(
                pokemon = null
            )
        }
    }
    private fun obtainPokemonData(pokemonName: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = pokemonDataSource.getDetail(pokemonName)
            _state.update { state ->
                state.copy(
                    pokemon = result
                )
            }
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
