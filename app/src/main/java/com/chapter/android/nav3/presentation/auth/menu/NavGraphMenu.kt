package com.chapter.android.nav3.presentation.auth.menu

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.presentation.auth.ProductGraph
import com.chapter.android.nav3.presentation.auth.menu.menu.MenuScreen
import com.chapter.android.nav3.presentation.auth.menu.submenu.SubMenuScreen
import com.chapter.android.nav3.presentation.auth.product.Cart
import com.chapter.android.nav3.presentation.auth.product.List

@Composable
fun NavGraphMenu(
    outerBackStack: MutableList<NavKey>,
    navToLogout: () -> Unit,
) {
    val inner = rememberNavBackStack(Menu)
    NavDisplay(inner, entryProvider = entryProvider {
        entry<Menu> {
            MenuScreen(
                onProducts = { inner.add(SubMenu) },
                onLogout = navToLogout
            )
        }
        entry<SubMenu> {
            SubMenuScreen(
                onList = { outerBackStack.add(ProductGraph(List)) },
                onCart = { outerBackStack.add(ProductGraph(Cart)) }
            )
        }
    })
}

