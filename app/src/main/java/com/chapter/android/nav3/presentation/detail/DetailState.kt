package com.chapter.android.nav3.presentation.detail

import androidx.compose.runtime.Stable
import androidx.compose.runtime.Immutable

@Stable
data class DetailState(
    val isLoading: Boolean = false,
    val dialog: Dialog = Dialog.NoDialog
) {
    @Immutable
    sealed interface Dialog {
        object NoDialog : Dialog
        object NoConnected : Dialog
        object General : Dialog
    }
}