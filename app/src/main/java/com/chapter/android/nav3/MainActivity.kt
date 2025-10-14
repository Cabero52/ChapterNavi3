package com.chapter.android.nav3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.chapter.android.nav3.data.datasource.PokemonRemoteDataSource
import com.chapter.android.nav3.data.network.KtorClientFactory
import com.chapter.android.nav3.data.network.KtorPokemonApi
import com.chapter.android.nav3.presentation.list.ListScreen
import com.chapter.android.nav3.presentation.list.ListViewModel
import com.chapter.android.nav3.ui.theme.ChapterNavi3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChapterNavi3Theme {
                ListScreen(
                    viewModel = ListViewModel(
                        pokemonDataSource = PokemonRemoteDataSource(
                            api = KtorPokemonApi(
                                client = KtorClientFactory.build()
                            )
                        )
                    ),
                    onBack = {}
                )
            }
        }
    }
}