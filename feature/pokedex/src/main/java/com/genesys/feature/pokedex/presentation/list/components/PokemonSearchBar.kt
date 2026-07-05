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
import androidx.compose.ui.graphics.Color
import com.genesys.core.designsystem.theme.neoShadow
import com.genesys.core.designsystem.theme.AppTheme
import androidx.compose.ui.res.stringResource
import com.genesys.feature.pokedex.R

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
            val shape = RoundedCornerShape(14.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .neoShadow(color = AppTheme.colorScheme.neoBorder, shape = shape)
                    .background(
                        color = AppTheme.colorScheme.colorBgElevated,
                        shape = shape
                    )
                    .border(
                        width = AppTheme.strokes.stroke2,
                        color = AppTheme.colorScheme.neoBorder,
                        shape = shape
                    )
                    .padding(horizontal = AppTheme.spacing.md, vertical = AppTheme.spacing.md),
                contentAlignment = Alignment.CenterStart
            ) {
                if (query.isEmpty()) {
                    AppText(
                        text = stringResource(R.string.pokedex_search_placeholder),
                        color = AppTheme.colorScheme.colorBorder
                    )
                }
                innerTextField()
            }
        }
    )
}
