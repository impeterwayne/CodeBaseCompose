package com.genesys.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.genesys.core.designsystem.theme.GenesysTheme
import java.util.Locale

@Composable
fun GenesysChip(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues? = null
) {
    val colors = GenesysTheme.colors
    val resolvedContentPadding = contentPadding ?: PaddingValues(
        horizontal = GenesysTheme.spacing.sm,
        vertical = GenesysTheme.spacing.xs
    )
    val backgroundColor = if (selected) colors.primary else colors.surfaceContainerLowest
    val contentColor = if (selected) colors.onPrimary else colors.primary

    Box(
        modifier = modifier
            .background(backgroundColor)
            .border(GenesysTheme.strokes.thin, colors.primary, GenesysTheme.shapes.small)
            .padding(resolvedContentPadding)
    ) {
        GenesysText(
            text = text.uppercase(Locale.ROOT),
            style = GenesysTheme.typography.labelSmall,
            color = contentColor
        )
    }
}
