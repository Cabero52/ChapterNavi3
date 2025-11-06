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
import com.chapter.android.nav3.presentation.list.ListViewModel
import com.chapter.android.nav3.presentation.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavGraph() {
    val favorites = remember { mutableMapOf<String, Boolean>() }

    val backStack = remember { mutableStateListOf<Any>(SplashPokemonDestination) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<SplashPokemonDestination> {
                SplashScreen(
                    goToPokemonList = {
                        /* Comment with test 2 and 4 */
                        //backStack.clear()
                        backStack.add(ListPokemonDestination)
                    }
                )
            }
            entry<ListPokemonDestination> {
                val vm: ListViewModel = koinViewModel(
                    parameters = { parametersOf(favorites) }
                )
                ListScreen(
                    viewModel = vm,
                    onItem = {
                        /* 1. Nav simple */
                        backStack.add(DetailPokemonDestination(it))
                        /* 2. Nav with popUp */
                        //backStack.replace(DetailPokemonDestination(it))
                    },
                    onBack = { backStack.removeLastOrNull() }
                )
            }
            entry<DetailPokemonDestination> {
                val vm: DetailViewModel = koinViewModel(
                    parameters = { parametersOf(it.id) }
                )
                DetailScreen(
                    viewModel = vm,
                    onBack = { result ->
                        /* 3. Back con Result */
                        favorites[result.name] = result.isFavorite
                        backStack.removeLastOrNull()
                        /* 4. Back -2 */
                        //backStack.pop(2)
                    },
                )
            }
        }

    )
}

fun MutableList<Any>.replace(newKey: Any) {
    if (isEmpty()) add(newKey) else this[lastIndex] = newKey
}

fun MutableList<Any>.pop(times: Int = 1) {
    repeat(times.coerceAtLeast(0)) { if (isNotEmpty()) removeAt(lastIndex) }
}