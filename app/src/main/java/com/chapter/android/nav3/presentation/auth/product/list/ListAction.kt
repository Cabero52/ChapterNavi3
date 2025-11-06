package com.chapter.android.nav3.presentation.auth.product.list

sealed interface ListAction {
    object OnRetry : ListAction
    object OnDialogDismiss : ListAction
}