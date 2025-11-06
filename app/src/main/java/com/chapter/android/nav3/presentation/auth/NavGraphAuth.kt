package com.chapter.android.nav3.presentation.auth

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.presentation.UnAuthGraph
import com.chapter.android.nav3.presentation.auth.menu.NavGraphMenu
import com.chapter.android.nav3.presentation.auth.product.NavGraphProduct

@Composable
fun NavGraphAuth(outerBackStack: MutableList<NavKey>) {
    val inner = rememberNavBackStack(MenuGraph)
    NavDisplay(inner, entryProvider = entryProvider {
        entry<MenuGraph> {
            NavGraphMenu(
                inner,
                navToLogout = {
                    outerBackStack.clear()
                    outerBackStack.add(UnAuthGraph)
                }
            )
        }
        entry<ProductGraph> {
            NavGraphProduct(
                start = it.route,
                outerBackStack = outerBackStack
            )
        }
    })
}

