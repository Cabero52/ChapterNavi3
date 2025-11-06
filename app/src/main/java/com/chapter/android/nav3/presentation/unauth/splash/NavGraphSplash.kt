package com.chapter.android.nav3.presentation.unauth.splash

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.presentation.unauth.LoginGraph

@Composable
fun NavGraphSplash(outerBackStack: MutableList<NavKey>) {
    val inner = rememberNavBackStack(Splash)
    NavDisplay(inner, entryProvider = entryProvider {
        entry<Splash> {
            SplashScreen(
                goToPokemonList = {
                    outerBackStack.clear()
                    outerBackStack.add(LoginGraph)
                }
            )
        }
    })
}
