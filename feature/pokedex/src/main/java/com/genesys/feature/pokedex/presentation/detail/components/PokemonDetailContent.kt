package com.genesys.feature.pokedex.presentation.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.AppDivider
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.pokedex.PokemonInfo
import com.genesys.feature.pokedex.R
import com.genesys.feature.pokedex.presentation.common.components.CustomCircularProgressIndicator
import com.skydoves.landscapist.glide.GlideImage

import com.genesys.core.designsystem.theme.neoShadow

/**
 * Extension property to map a Pokemon type string to its corresponding theme color.
 */
private val String.pokemonTypeColor: Color
    get() = when (this.lowercase()) {
        "fire" -> Color(0xFFFF5722)
        "water" -> Color(0xFF2196F3)
        "grass" -> Color(0xFF4CAF50)
        "electric" -> Color(0xFFFFEB3B)
        "poison" -> Color(0xFF9C27B0)
        "flying" -> Color(0xFF03A9F4)
        "bug" -> Color(0xFF8BC34A)
        "normal" -> Color(0xFF9E9E9E)
        "ground" -> Color(0xFFFFC107)
        "fairy" -> Color(0xFFE91E63)
        "fight", "fighting" -> Color(0xFFD32F2F)
        "psychic" -> Color(0xFFFF4081)
        "rock" -> Color(0xFF795548)
        "steel" -> Color(0xFF607D8B)
        "ice" -> Color(0xFF00BCD4)
        "ghost" -> Color(0xFF673AB7)
        "dragon" -> Color(0xFF3F51B5)
        else -> Color(0xFF009688)
    }

/**
 * Main detail screen content for displaying Pokémon details.
 * Structured with smaller, focused components to maintain clean readable layout code.
 */
@Composable
fun PokemonDetailContent(
    pokemonInfo: PokemonInfo,
    modifier: Modifier = Modifier
) {
    val primaryTypeColor = remember(pokemonInfo.types) {
        pokemonInfo.types.firstOrNull()?.pokemonTypeColor ?: Color(0xFF009688)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = AppTheme.spacing.xl),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.md)
    ) {
        // 1. Pokémon Artwork Section
        PokemonArtworkSection(
            pokemonInfo = pokemonInfo,
            primaryTypeColor = primaryTypeColor
        )

        // 2. Identity Info (Id, Name, Type Badges)
        PokemonIdentitySection(
            pokemonInfo = pokemonInfo,
            primaryTypeColor = primaryTypeColor
        )

        // 3. Metrics (Height & Weight Panels)
        PokemonMetricsSection(
            pokemonInfo = pokemonInfo
        )

        // 4. Base Stats Card
        PokemonStatsSection(
            pokemonInfo = pokemonInfo
        )
    }
}

/**
 * Renders the Pokemon artwork inside a vertical gradient card that matches the Pokemon's primary type.
 */
@Composable
private fun PokemonArtworkSection(
    pokemonInfo: PokemonInfo,
    primaryTypeColor: Color,
    modifier: Modifier = Modifier
) {
    val shape = AppTheme.shapes.shape6
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .neoShadow(color = Color.Black, shape = shape)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        primaryTypeColor.copy(alpha = 0.25f),
                        primaryTypeColor.copy(alpha = 0.05f)
                    )
                ),
                shape = shape
            )
            .border(
                width = AppTheme.strokes.stroke2,
                color = Color.Black,
                shape = shape
            )
            .clip(shape),
        contentAlignment = Alignment.Center
    ) {
        // Radial glow effect behind the artwork
        Box(
            modifier = Modifier
                .size(180.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            primaryTypeColor.copy(alpha = 0.4f),
                            Color.Transparent
                        )
                    )
                )
        )

        val indexString = pokemonInfo.id.toString()
        val artworkUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$indexString.png"

        GlideImage(
            imageModel = { artworkUrl },
            previewPlaceholder = painterResource(id = android.R.drawable.ic_menu_gallery),
            modifier = Modifier.size(200.dp),
            loading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CustomCircularProgressIndicator(
                        color = primaryTypeColor,
                        modifier = Modifier.size(40.dp)
                    )
                }
            },
            failure = {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = stringResource(R.string.pokedex_failed_to_load_image),
                    modifier = Modifier.size(60.dp),
                    colorFilter = ColorFilter.tint(AppTheme.colorScheme.colorBorder)
                )
            }
        )
    }
}

/**
 * Displays the Pokédex index, display name, and type badges.
 */
@Composable
private fun PokemonIdentitySection(
    pokemonInfo: PokemonInfo,
    primaryTypeColor: Color,
    modifier: Modifier = Modifier
) {
    AppPanel(
        modifier = modifier.fillMaxWidth(),
        tone = AppPanelTone.Raised,
        contentPadding = PaddingValues(AppTheme.spacing.md)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Pokédex ID (e.g. #001)
            AppText(
                text = "#${pokemonInfo.id.toString().padStart(3, '0')}",
                style = AppTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                color = primaryTypeColor
            )
            
            Spacer(modifier = Modifier.height(AppTheme.spacing.xs))
            
            // Name
            AppText(
                text = pokemonInfo.getDisplayName(),
                style = AppTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
                color = AppTheme.colorScheme.colorText
            )
            
            Spacer(modifier = Modifier.height(AppTheme.spacing.sm))

            // Types Badge Row
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.xs),
                verticalAlignment = Alignment.CenterVertically
            ) {
                pokemonInfo.types.forEach { type ->
                    val typeColor = type.pokemonTypeColor
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(typeColor.copy(alpha = 0.15f))
                            .border(width = 1.dp, color = typeColor, shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = AppTheme.spacing.md, vertical = AppTheme.spacing.xs)
                    ) {
                        AppText(
                            text = type.uppercase(),
                            color = typeColor,
                            style = AppTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Displays the height and weight details of the Pokemon side-by-side.
 */
@Composable
private fun PokemonMetricsSection(
    pokemonInfo: PokemonInfo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.md)
    ) {
        MetricPanel(
            title = stringResource(R.string.pokedex_height),
            value = pokemonInfo.getFormattedHeight(),
            modifier = Modifier.weight(1f)
        )
        MetricPanel(
            title = stringResource(R.string.pokedex_weight),
            value = pokemonInfo.getFormattedWeight(),
            modifier = Modifier.weight(1f)
        )
    }
}

/**
 * Reusable panel component for representing a key-value metric card.
 */
@Composable
private fun MetricPanel(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    AppPanel(
        modifier = modifier,
        tone = AppPanelTone.Raised,
        contentPadding = PaddingValues(AppTheme.spacing.md)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                text = title,
                style = AppTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = AppTheme.colorScheme.colorBorder
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.xs))
            AppText(
                text = value,
                style = AppTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = AppTheme.colorScheme.colorText
            )
        }
    }
}

/**
 * Displays the base stats card including HP, Attack, Defense, Speed, and Experience.
 */
@Composable
private fun PokemonStatsSection(
    pokemonInfo: PokemonInfo,
    modifier: Modifier = Modifier
) {
    AppPanel(
        modifier = modifier.fillMaxWidth(),
        tone = AppPanelTone.Raised,
        contentPadding = PaddingValues(AppTheme.spacing.lg)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.md)
        ) {
            AppText(
                text = stringResource(R.string.pokedex_base_stats),
                style = AppTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = AppTheme.colorScheme.colorText
            )

            AppDivider(color = AppTheme.colorScheme.colorBorder.copy(alpha = 0.2f))

            // Stats rows
            PokemonStatRow(name = stringResource(R.string.pokedex_hp), value = pokemonInfo.hp, max = 150, color = Color(0xFF4CAF50))
            PokemonStatRow(name = stringResource(R.string.pokedex_attack), value = pokemonInfo.attack, max = 150, color = Color(0xFFFF5722))
            PokemonStatRow(name = stringResource(R.string.pokedex_defense), value = pokemonInfo.defense, max = 150, color = Color(0xFF2196F3))
            PokemonStatRow(name = stringResource(R.string.pokedex_speed), value = pokemonInfo.speed, max = 150, color = Color(0xFFFFEB3B))
            PokemonStatRow(name = stringResource(R.string.pokedex_experience), value = pokemonInfo.baseExperience, max = 300, color = Color(0xFF9C27B0))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailContentLightPreview() {
    AppTheme(darkTheme = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.colorBgLayout)
                .padding(16.dp)
        ) {
            PokemonDetailContent(
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
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailContentDarkPreview() {
    AppTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.colorBgLayout)
                .padding(16.dp)
        ) {
            PokemonDetailContent(
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
            )
        }
    }
}
