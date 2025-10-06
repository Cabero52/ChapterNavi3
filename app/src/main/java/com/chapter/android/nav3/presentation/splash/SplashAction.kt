package com.chapter.android.nav3.presentation.splash

sealed interface SplashAction {
    object OnBack : SplashAction
    object OnRetry : SplashAction
    object OnDialogDismiss : SplashAction
}