package com.chapter.android.nav3.presentation.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DetailViewModel(
) : ViewModel() {


    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state

    init {

    }

    fun onAction(action: DetailActions) {
        when (action) {
            DetailActions.OnBack -> TODO()
            DetailActions.OnDialogDismiss -> dismissDialog()
            DetailActions.OnRetry -> TODO()
            else -> Unit
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
                dialog = DetailState.Dialog.NoDialog
            )
        }
    }
}
