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
    val colors = GenesysTheme.colorScheme
    val resolvedContentPadding = contentPadding ?: PaddingValues(
        horizontal = GenesysTheme.spacing.sm,
        vertical = GenesysTheme.spacing.xs
    )
    val backgroundColor = if (selected) colors.colorPrimary else colors.colorBgContainerest
    val contentColor = if (selected) colors.colorTextOnPrimary else colors.colorPrimary

    Box(
        modifier = modifier
            .background(backgroundColor)
            .border(GenesysTheme.strokes.thin, colors.colorPrimary, GenesysTheme.shapes.small)
            .padding(resolvedContentPadding)
    ) {
        GenesysText(
            text = text.uppercase(Locale.ROOT),
            style = GenesysTheme.typography.labelSmall,
            color = contentColor
        )
    }
}
