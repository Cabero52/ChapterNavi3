package com.chapter.android.nav3.presentation.list

import com.chapter.android.nav3.data.models.PokemonDetailResponse

sealed interface ListAction {
    object OnBack : ListAction
    object OnRetry : ListAction
    object OnDialogDismiss : ListAction
    object ResetPokemon : ListAction
    data class GetPokemonDetail(val pokemonName: String) : ListAction
    data class GoToPokemonDetail(val pokemon: PokemonDetailResponse) : ListAction
}