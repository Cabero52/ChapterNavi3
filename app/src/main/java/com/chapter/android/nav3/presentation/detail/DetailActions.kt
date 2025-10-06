package com.chapter.android.nav3.presentation.detail

sealed interface DetailActions {
    object OnBack : DetailActions
    object OnRetry : DetailActions
    object OnDialogDismiss : DetailActions
}