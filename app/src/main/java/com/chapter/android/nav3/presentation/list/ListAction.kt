package com.chapter.android.nav3.presentation.list

sealed interface ListAction {
    object LoadData : ListAction
    object OnBack : ListAction
    object OnRetry : ListAction
    object OnDialogDismiss : ListAction
}