package com.chapter.android.nav3.presentation.list

import androidx.compose.runtime.Stable
import androidx.compose.runtime.Immutable
import com.chapter.android.nav3.data.models.PokemonDetailResponse
import com.chapter.android.nav3.data.models.PokemonDto

@Stable
data class ListState(
    val isLoading: Boolean = false,
    val dialog: Dialog = Dialog.NoDialog,
    val items: List<PokemonDto> = emptyList(),
    val pokemon: PokemonDetailResponse? = null
) {
    @Immutable
    sealed interface Dialog {
        object NoDialog : Dialog
        object NoConnected : Dialog
        object General : Dialog
    }
}