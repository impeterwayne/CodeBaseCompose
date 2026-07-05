package com.genesys.feature.pokedex.presentation.list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.feature.pokedex.R

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
                text = stringResource(R.string.pokedex_pokedex),
                style = AppTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = AppTheme.colorScheme.colorText
            )
            AppText(
                text = stringResource(R.string.pokedex_search_subtitle),
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colorScheme.colorBorder
            )
        }
    }
}
