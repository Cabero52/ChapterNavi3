package com.chapter.android.nav3.presentation.detail

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.alpha
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chapter.android.nav3.data.models.PokemonDetailResponse
import com.kmpalette.rememberPaletteState
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    pokemon: PokemonDetailResponse,
    viewModel: DetailViewModel = koinViewModel(),
    onBack: () -> Unit = {},
) {
    val state by viewModel.state.collectAsState()

    // Lógica de acciones
    val onAction by remember {
        mutableStateOf<(DetailActions) -> Unit>({ action ->
            when (action) {
                DetailActions.OnBack -> onBack()
                else -> viewModel.onAction(action)
            }
        })
    }

    BackHandler {
        onAction(DetailActions.OnBack)
    }
    DetailContentScreen(
        pokemon = pokemon,
        state = state,
        onAction = onAction
    )
    if (state.isLoading) {

    }
    when (state.dialog) {
        DetailState.Dialog.NoConnected -> {

        }

        DetailState.Dialog.General -> {

        }

        else -> Unit
    }
}

@Composable
fun DetailContentScreen(
    pokemon: PokemonDetailResponse,
    modifier: Modifier = Modifier,
    state: DetailState,
    onAction: (DetailActions) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(24.dp))
            .background(DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val dominantColor = remember { mutableStateOf(DarkGray) }
        val secondaryColor = remember { mutableStateOf(DarkGray) }
        val backgroundBrush = Brush.verticalGradient(
            colors = listOf(secondaryColor.value, dominantColor.value)
        )

        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(backgroundBrush)
                .padding(top = 24.dp),
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

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            text = pokemon.name.capitalize(Locale.current),
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Text(text = pokemon.height.toString())
    }
}
