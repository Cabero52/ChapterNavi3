package com.chapter.android.nav3.presentation.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ListViewModel(
) : ViewModel() {

    private val _state = MutableStateFlow(ListState())
    val state: StateFlow<ListState> = _state

    init {

    }

    fun onAction(action: ListAction) {
        when (action) {
            ListAction.OnBack -> TODO()
            ListAction.OnDialogDismiss -> dismissDialog()
            ListAction.OnRetry -> TODO()
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
                dialog = ListState.Dialog.NoDialog
            )
        }
    }
}
