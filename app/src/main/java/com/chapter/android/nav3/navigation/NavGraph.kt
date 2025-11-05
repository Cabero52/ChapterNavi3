package com.chapter.android.nav3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.presentation.detail.DetailScreen
import com.chapter.android.nav3.presentation.detail.DetailViewModel
import com.chapter.android.nav3.presentation.list.ListScreen
import com.chapter.android.nav3.presentation.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavGraph() {

    val backStack = remember { mutableStateListOf<Any>(SplashPokemonDestination) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<SplashPokemonDestination> {
                SplashScreen(
                    goToPokemonList = { backStack.add(ListPokemonDestination) }
                )
            }
            entry<ListPokemonDestination> {
                ListScreen(
                    onItem = { backStack.add(DetailPokemonDestination(it)) },
                    onBack = { backStack.removeLastOrNull() }
                )
            }
            entry<DetailPokemonDestination> {
                val vm: DetailViewModel = koinViewModel(
                    parameters = { parametersOf(it.id) }
                )
                DetailScreen(
                    viewModel = vm,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }

    )
}