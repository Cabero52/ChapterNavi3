package com.chapter.android.nav3.presentation.auth.product.detail

sealed interface DetailActions {
    object OnRetry : DetailActions
    object OnDialogDismiss : DetailActions
}