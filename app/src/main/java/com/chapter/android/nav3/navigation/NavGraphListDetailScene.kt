package com.chapter.android.nav3.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.presentation.detail.DetailScreen
import com.chapter.android.nav3.presentation.detail.DetailViewModel
import com.chapter.android.nav3.presentation.list.ListScreen
import com.chapter.android.nav3.presentation.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun NavGraphListDetailScene() {

    val backStack = rememberNavBackStack(SplashPokemonDestination)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        sceneStrategy = rememberListDetailSceneStrategy(),
        entryProvider = entryProvider {
            entry<SplashPokemonDestination> {
                SplashScreen(
                    goToPokemonList = {
                        backStack.clear()
                        backStack.add(ListPokemonDestination)
                    }
                )
            }
            entry<ListPokemonDestination>(
                metadata = ListDetailSceneStrategy.listPane()
            ) {
                ListScreen(
                    onItem = { backStack.add(DetailPokemonDestination(it)) },
                    onBack = { backStack.removeLastOrNull() }
                )
            }
            entry<DetailPokemonDestination>(
                metadata = ListDetailSceneStrategy.detailPane()
            ) {
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
