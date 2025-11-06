package com.chapter.android.nav3.presentation.unauth

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.presentation.AuthGraph
import com.chapter.android.nav3.presentation.unauth.login.NavGraphLogin
import com.chapter.android.nav3.presentation.unauth.splash.NavGraphSplash

@Composable
fun NavGraphUnAuth(outerBackStack: MutableList<NavKey>) {
    val inner = rememberNavBackStack(SplashGraph)
    NavDisplay(inner, entryProvider = entryProvider {
        entry<SplashGraph> {
            NavGraphSplash(inner)
        }
        entry<LoginGraph> {
            NavGraphLogin(
                inner,
                navToAuth = {
                    outerBackStack.clear()
                    outerBackStack.add(AuthGraph)
                })
        }
    })
}
