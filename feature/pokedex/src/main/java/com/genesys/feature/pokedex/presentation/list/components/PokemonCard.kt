package com.genesys.feature.pokedex.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.pokedex.Pokemon
import com.genesys.feature.pokedex.presentation.common.components.CustomCircularProgressIndicator
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppPanel(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        tone = AppPanelTone.Raised,
        onClick = onClick,
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            AppTheme.colorScheme.colorBgElevated,
                            AppTheme.colorScheme.colorBgElevated.copy(alpha = 0.8f)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(AppTheme.spacing.md),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Index & Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppText(
                        text = pokemon.getFormattedNumber(),
                        style = AppTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = AppTheme.colorScheme.colorBorder
                    )
                }
                
                GlideImage(
                    imageModel = { pokemon.imageUrl },
                    previewPlaceholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                    modifier = Modifier
                        .size(80.dp)
                        .padding(bottom = AppTheme.spacing.xs),
                    loading = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0x0FFFFFFF)),
                            contentAlignment = Alignment.Center
                        ) {
                            CustomCircularProgressIndicator(
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(24.dp),
                                color = AppTheme.colorScheme.colorPrimary
                            )
                        }
                    },
                    failure = {
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                            contentDescription = "Failed to load",
                            colorFilter = ColorFilter.tint(AppTheme.colorScheme.colorBorder),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )

                // Pokemon Name
                AppText(
                    text = pokemon.getDisplayName(),
                    style = AppTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = AppTheme.colorScheme.colorText
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonCardLightPreview() {
    AppTheme(darkTheme = false) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .background(AppTheme.colorScheme.colorBgLayout)
                .padding(16.dp)
        ) {
            PokemonCard(
                pokemon = Pokemon(page = 1, name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonCardDarkPreview() {
    AppTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .background(AppTheme.colorScheme.colorBgLayout)
                .padding(16.dp)
        ) {
            PokemonCard(
                pokemon = Pokemon(page = 1, name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
                onClick = {}
            )
        }
    }
}
