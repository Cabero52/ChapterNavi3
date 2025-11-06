package com.chapter.android.nav3.presentation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.presentation.auth.NavGraphAuth
import com.chapter.android.nav3.presentation.unauth.NavGraphUnAuth

@Composable
fun NavGraph() {
    val backStack = rememberNavBackStack(UnAuthGraph)

    NavDisplay(backStack, entryProvider = entryProvider {
        entry<UnAuthGraph> {
            NavGraphUnAuth(backStack)
        }
        entry<AuthGraph> {
            NavGraphAuth(backStack)
        }
    })
}
