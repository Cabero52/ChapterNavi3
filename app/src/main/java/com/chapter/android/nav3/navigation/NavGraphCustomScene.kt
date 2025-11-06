package com.chapter.android.nav3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.SinglePaneSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.navigation.scene.TwoPaneScene
import com.chapter.android.nav3.navigation.scene.rememberTwoPaneSceneStrategy
import com.chapter.android.nav3.presentation.detail.DetailScreen
import com.chapter.android.nav3.presentation.detail.DetailViewModel
import com.chapter.android.nav3.presentation.list.ListScreen
import com.chapter.android.nav3.presentation.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavGraphCustomScene() {

    val backStack = rememberNavBackStack(SplashPokemonDestination)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        sceneStrategy = rememberTwoPaneSceneStrategy(),
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
                metadata = TwoPaneScene.twoPane()
            ) {
                ListScreen(
                    onItem = { backStack.add(DetailPokemonDestination(it)) },
                    onBack = { backStack.removeLastOrNull() }
                )
            }
            entry<DetailPokemonDestination>(
                metadata = TwoPaneScene.twoPane()
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
