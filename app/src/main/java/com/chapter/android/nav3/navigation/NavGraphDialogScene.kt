package com.chapter.android.nav3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.presentation.detail.DetailScreen
import com.chapter.android.nav3.presentation.detail.DetailViewModel
import com.chapter.android.nav3.presentation.list.ListScreen
import com.chapter.android.nav3.presentation.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavGraphDialogScene(
    modifier: Modifier = Modifier
) {

    val backStack = rememberNavBackStack(SplashPokemonDestination)

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        //sceneStrategy = SinglePaneSceneStrategy(),
        sceneStrategy = DialogSceneStrategy(),
        entryProvider = entryProvider {
            entry<SplashPokemonDestination>{
                SplashScreen(
                    goToPokemonList = {
                        backStack.clear()
                        backStack.add(ListPokemonDestination)
                    }
                )
            }
            entry<ListPokemonDestination> {
                ListScreen(
                    onItem = { backStack.add(DetailPokemonDestination(it)) },
                    onBack = { backStack.removeLastOrNull() }
                )
            }
            entry<DetailPokemonDestination>(
                metadata = DialogSceneStrategy.dialog(
                    dialogProperties =  DialogProperties()
                )
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
