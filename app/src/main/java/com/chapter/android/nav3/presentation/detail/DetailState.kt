package com.chapter.android.nav3.presentation.detail

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.chapter.android.nav3.data.models.PokemonDetailResponse

@Stable
data class DetailState(
    val view: View = View.Loading,
    val dialog: Dialog = Dialog.NoDialog
) {
    @Immutable
    sealed interface View {
        data object Loading : View
        data class Loaded(val pokemon: PokemonDetailResponse) : View
    }

    @Immutable
    sealed interface Dialog {
        data object NoDialog : Dialog
        data object NoConnected : Dialog
        data object General : Dialog
    }
}