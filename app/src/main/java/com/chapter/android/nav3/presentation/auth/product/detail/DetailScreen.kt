package com.chapter.android.nav3.presentation.auth.product.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chapter.android.nav3.data.models.PokemonDetailResponse
import com.chapter.android.nav3.presentation.auth.product.detail.composables.PokemonHeightWeight
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin

@Composable
fun DetailScreen(
    viewModel: DetailViewModel
) {
    val state by viewModel.state.collectAsState()

    val onAction by rememberUpdatedState(viewModel::onAction)

    when (state.view) {
        DetailState.View.Loading -> Unit
        is DetailState.View.Loaded -> {
            DetailContentScreen(
                pokemon = (state.view as DetailState.View.Loaded).pokemon,
                onAction = onAction
            )
        }
    }
    when (state.dialog) {
        DetailState.Dialog.NoConnected -> {

        }

        DetailState.Dialog.General -> {

        }

        else -> Unit
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContentScreen(
    modifier: Modifier = Modifier,
    pokemon: PokemonDetailResponse,
    onAction: (DetailActions) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Transparent,
                ),
                title = {
                    Text(text = "Pokedex", color = DarkGray.copy(0.8f), fontSize = 14.sp)
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {  },
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "onBackIcon",
                        tint = DarkGray.copy(0.8f)
                    )
                },
                actions = {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = "Nº ${pokemon.id}",
                        color = DarkGray.copy(0.8f),
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Italic
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .clip(RoundedCornerShape(24.dp))
                .background(DarkGray.copy(0.8f)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val dominantColor = remember { mutableStateOf(DarkGray) }
            val secondaryColor = remember { mutableStateOf(DarkGray) }
            val backgroundBrush = Brush.verticalGradient(
                colors = listOf(secondaryColor.value, dominantColor.value)
            )

            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(backgroundBrush)
                        .padding(top = 50.dp),
                contentAlignment = Alignment.Center
            ) {
                GlideImage(
                    modifier = Modifier
                        .size(150.dp),
                    imageModel = { pokemon.image },
                    component = rememberImageComponent {
                        +PalettePlugin(
                            imageModel = pokemon.image,
                            useCache = true,
                            paletteLoadedListener = { palette ->
                                palette.dominantSwatch?.rgb?.let { rgb ->
                                    dominantColor.value = Color(rgb)
                                }
                                val secRgb = palette.lightVibrantSwatch?.rgb
                                    ?: palette.mutedSwatch?.rgb
                                if (secRgb != null) {
                                    secondaryColor.value = Color(secRgb)
                                } else {
                                    secondaryColor.value = dominantColor.value
                                }
                            }
                        )
                    }
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                text = pokemon.name.capitalize(Locale.current),
                fontSize = 36.sp,
                fontStyle = FontStyle.Italic,
                color = secondaryColor.value,
                textAlign = TextAlign.Center,
            )
            PokemonHeightWeight(
                height = pokemon.height / 10f,
                weight = pokemon.weight / 10f,
                textColor = secondaryColor.value
            )
        }
    }
}