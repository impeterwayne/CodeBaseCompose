package com.genesys.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.genesys.core.designsystem.theme.GenesysTheme
import java.util.Locale

@Composable
fun GenesysPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues? = null
) {
    val colors = GenesysTheme.colors
    val resolvedContentPadding = contentPadding ?: PaddingValues(
        horizontal = GenesysTheme.spacing.lg,
        vertical = GenesysTheme.spacing.sm
    )
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .clip(GenesysTheme.shapes.medium)
            .background(colors.primary)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(resolvedContentPadding)
    ) {
        GenesysText(
            text = text.uppercase(Locale.ROOT),
            style = GenesysTheme.typography.labelLarge,
            color = colors.onPrimary
        )
    }
}

@Composable
fun GenesysSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues? = null
) {
    val colors = GenesysTheme.colors
    val resolvedContentPadding = contentPadding ?: PaddingValues(
        horizontal = GenesysTheme.spacing.lg,
        vertical = GenesysTheme.spacing.sm
    )
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .clip(GenesysTheme.shapes.medium)
            .background(colors.surface)
            .border(
                width = GenesysTheme.strokes.medium,
                color = colors.primary,
                shape = GenesysTheme.shapes.medium
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(resolvedContentPadding)
    ) {
        GenesysText(
            text = text.uppercase(Locale.ROOT),
            style = GenesysTheme.typography.labelLarge,
            color = colors.primary
        )
    }
}
