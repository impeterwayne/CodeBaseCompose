package com.genesys.feature.pokedex.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme

@Composable
fun PokedexHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = AppTheme.spacing.md),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            AppText(
                text = "Pokedex",
                style = AppTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = AppTheme.colorScheme.colorText
            )
            AppText(
                text = "Search Pokémon to view stats",
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colorScheme.colorBorder
            )
        }
    }
}
