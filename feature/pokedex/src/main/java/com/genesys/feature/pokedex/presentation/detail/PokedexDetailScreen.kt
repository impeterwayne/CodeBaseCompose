package com.genesys.feature.pokedex.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.genesys.core.designsystem.component.AppGradientTransition
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.component.ErrorState
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.designsystem.theme.neoShadow
import com.genesys.core.model.pokedex.PokemonInfo
import com.genesys.feature.pokedex.R
import com.genesys.feature.pokedex.presentation.common.components.CustomCircularProgressIndicator
import com.genesys.feature.pokedex.presentation.detail.components.PokemonDetailContent
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun PokedexDetailRoute(
    pokedexId: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.collectAsState()

    LaunchedEffect(pokedexId) {
        viewModel.loadPokemon(pokedexId)
    }

    PokedexDetailScreen(
        uiState = uiState,
        onBack = onBack,
        onRetry = { viewModel.onAction(DetailAction.LoadPokemonDetail) },
        modifier = modifier
    )
}

@Composable
fun PokedexDetailScreen(
    uiState: DetailUiState,
    onBack: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.colorBgLayout)
            .statusBarsPadding()
    ) {
        // Pinned header container for BackActionButton
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.spacing.md, vertical = AppTheme.spacing.md)
        ) {
            BackActionButton(
                onBack = onBack
            )
        }

        // Scrollable content area with gradient overlay at the top
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = AppTheme.spacing.md)
            ) {
                Spacer(modifier = Modifier.height(AppTheme.spacing.md))
                DetailStateContent(
                    uiState = uiState,
                    onRetry = onRetry
                )
            }

            AppGradientTransition(
                modifier = Modifier.align(Alignment.TopCenter),
                height = AppTheme.spacing.md
            )
        }
    }
}

@Composable
private fun BackActionButton(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val outlineColor = Color.Black
    val shape = CircleShape

    Box(
        modifier = modifier
            .size(48.dp)
            .neoShadow(color = outlineColor, offsetX = 4.dp, offsetY = 4.dp, shape = shape)
            .background(AppTheme.colorScheme.colorBgContainer, shape)
            .border(
                width = AppTheme.strokes.stroke2,
                color = outlineColor,
                shape = shape
            )
            .clip(shape)
            .clickable(
                onClick = onBack,
                onClickLabel = stringResource(R.string.pokedex_back_label),
                role = Role.Button
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = com.genesys.core.designsystem.R.drawable.ic_arrow_back),
            contentDescription = stringResource(R.string.pokedex_back),
            colorFilter = ColorFilter.tint(AppTheme.colorScheme.colorText),
            modifier = Modifier.size(24.dp)
        )
    }
}


@Composable
private fun DetailStateContent(
    uiState: DetailUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is DetailUiState.Loading -> {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentAlignment = Alignment.Center
            ) {
                CustomCircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    strokeWidth = 4.dp
                )
            }
        }

        is DetailUiState.Error -> {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentAlignment = Alignment.Center
            ) {
                ErrorState(
                    message = uiState.message ?: uiState.messageResId?.let { stringResource(it) }.orEmpty(),
                    onRetry = onRetry
                )
            }
        }

        is DetailUiState.Success -> {
            PokemonDetailContent(pokemonInfo = uiState.pokemonInfo)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokedexDetailScreenSuccessLightPreview() {
    AppTheme(darkTheme = false) {
        PokedexDetailScreen(
            uiState = DetailUiState.Success(
                pokemonInfo = PokemonInfo(
                    id = 1,
                    name = "bulbasaur",
                    height = 7,
                    weight = 69,
                    baseExperience = 64,
                    hp = 45,
                    attack = 49,
                    defense = 49,
                    speed = 45,
                    types = listOf("grass", "poison")
                )
            ),
            onBack = {},
            onRetry = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokedexDetailScreenSuccessDarkPreview() {
    AppTheme(darkTheme = true) {
        PokedexDetailScreen(
            uiState = DetailUiState.Success(
                pokemonInfo = PokemonInfo(
                    id = 1,
                    name = "bulbasaur",
                    height = 7,
                    weight = 69,
                    baseExperience = 64,
                    hp = 45,
                    attack = 49,
                    defense = 49,
                    speed = 45,
                    types = listOf("grass", "poison")
                )
            ),
            onBack = {},
            onRetry = {}
        )
    }
}
