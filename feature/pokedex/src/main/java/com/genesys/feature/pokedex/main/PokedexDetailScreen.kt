package com.genesys.feature.pokedex.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.genesys.core.designsystem.component.AppPageFrame
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.component.ErrorState
import com.genesys.core.designsystem.component.LoadingIndicator
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.pokedex.PokemonInfo
import com.genesys.feature.pokedex.R
import com.genesys.feature.pokedex.main.components.PokemonDetailContent

@Composable
fun PokedexDetailScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    PokedexDetailScreenContent(
        uiState = uiState,
        onBack = onBack,
        onRetry = { viewModel.loadPokemonDetail() },
        modifier = modifier
    )
}

@Composable
fun PokedexDetailScreenContent(
    uiState: DetailUiState,
    onBack: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppPageFrame(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.colorBgLayout),
        contentPadding = PaddingValues(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppTheme.spacing.md)
                .verticalScroll(rememberScrollState())
        ) {
            BackActionHeader(
                onBack = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = AppTheme.spacing.md)
            )

            DetailStateContent(
                uiState = uiState,
                onRetry = onRetry
            )
        }
    }
}

@Composable
private fun BackActionHeader(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppPanel(
        modifier = modifier,
        tone = AppPanelTone.Raised,
        onClick = onBack,
        onClickLabel = stringResource(R.string.pokedex_back_label),
        role = Role.Button,
        contentPadding = PaddingValues(AppTheme.spacing.md)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_revert),
                contentDescription = "Back",
                colorFilter = ColorFilter.tint(AppTheme.colorScheme.colorText),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(AppTheme.spacing.sm))
            AppText(
                text = "Back to Pokedex",
                style = AppTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                color = AppTheme.colorScheme.colorText
            )
        }
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
                LoadingIndicator()
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
                    message = uiState.message,
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
        PokedexDetailScreenContent(
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
        PokedexDetailScreenContent(
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
