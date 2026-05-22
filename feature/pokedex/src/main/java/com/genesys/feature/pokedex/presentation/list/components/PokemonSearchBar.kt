package com.genesys.feature.pokedex.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme

@Composable
fun PokemonSearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = AppTheme.spacing.md),
        textStyle = AppTheme.typography.bodyLarge.copy(
            color = AppTheme.colorScheme.colorText
        ),
        cursorBrush = SolidColor(AppTheme.colorScheme.colorPrimary),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = AppTheme.colorScheme.colorBgElevated,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = AppTheme.colorScheme.colorBorder.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = AppTheme.spacing.md, vertical = AppTheme.spacing.md),
                contentAlignment = Alignment.CenterStart
            ) {
                if (query.isEmpty()) {
                    AppText(
                        text = "Search Pokémon...",
                        color = AppTheme.colorScheme.colorBorder
                    )
                }
                innerTextField()
            }
        }
    )
}
