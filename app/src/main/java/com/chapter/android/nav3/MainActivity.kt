package com.chapter.android.nav3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChapterNavi3Theme {
        Greeting("Android")
    }
}