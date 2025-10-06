package com.chapter.android.nav3.presentation.splash

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SplashViewModel(
) : ViewModel() {


    private val _state = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state

    init {

    }

    fun onAction(action: SplashAction) {
        when (action) {
            SplashAction.OnBack -> TODO()
            SplashAction.OnDialogDismiss -> dismissDialog()
            SplashAction.OnRetry -> TODO()
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
                dialog = SplashState.Dialog.NoDialog
            )
        }
    }
}
