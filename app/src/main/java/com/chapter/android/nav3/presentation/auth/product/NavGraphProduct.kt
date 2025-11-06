package com.chapter.android.nav3.presentation.auth.product

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.presentation.auth.product.cart.CartScreen
import com.chapter.android.nav3.presentation.auth.product.detail.DetailScreen
import com.chapter.android.nav3.presentation.auth.product.detail.DetailViewModel
import com.chapter.android.nav3.presentation.auth.product.list.ListScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavGraphProduct(
    outerBackStack: MutableList<NavKey>,
    start: ProductRoute
) {
    val inner = rememberNavBackStack(start)
    NavDisplay(inner, entryProvider = entryProvider {
        entry<List> {
            ListScreen(
                onItem = { inner.add(Detail(it)) }
            )
        }
        entry<Detail> {
            val vm: DetailViewModel = koinViewModel(
                parameters = { parametersOf(it.id) }
            )
            DetailScreen(
                viewModel = vm
            )
        }
        entry<Cart> {
            CartScreen()
        }
    })
}

