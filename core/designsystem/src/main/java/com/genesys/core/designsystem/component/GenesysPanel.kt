package com.genesys.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.genesys.core.designsystem.theme.GenesysTheme

enum class GenesysPanelTone {
    Frame,
    Raised,
    Heavy,
    Error,
    Warning,
    Success
}

@Composable
fun GenesysPanel(
    modifier: Modifier = Modifier,
    tone: GenesysPanelTone = GenesysPanelTone.Raised,
    contentPadding: PaddingValues? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = GenesysTheme.colorScheme
    val strokes = GenesysTheme.strokes
    val shape = GenesysTheme.shapes.medium
    val resolvedContentPadding = contentPadding ?: PaddingValues(GenesysTheme.spacing.md)
    val interactionSource = remember { MutableInteractionSource() }
    val backgroundColor: Color
    val borderColor: Color
    val borderWidth = when (tone) {
        GenesysPanelTone.Frame -> strokes.thin
        GenesysPanelTone.Raised -> strokes.thin
        GenesysPanelTone.Heavy -> strokes.medium
        GenesysPanelTone.Error -> strokes.thin
        GenesysPanelTone.Warning -> strokes.thin
        GenesysPanelTone.Success -> strokes.thin
    }

    when (tone) {
        GenesysPanelTone.Frame -> {
            backgroundColor = colors.colorBgContainer
            borderColor = colors.colorBorderSecondary
        }
        GenesysPanelTone.Raised -> {
            backgroundColor = colors.colorBgContainer
            borderColor = colors.colorBorder
        }
        GenesysPanelTone.Heavy -> {
            backgroundColor = colors.colorPrimary
            borderColor = colors.colorPrimary
        }
        GenesysPanelTone.Error -> {
            backgroundColor = colors.colorBgElevated
            borderColor = colors.colorError
        }
        GenesysPanelTone.Warning -> {
            backgroundColor = colors.colorBgElevated
            borderColor = colors.colorWarning
        }
        GenesysPanelTone.Success -> {
            backgroundColor = colors.colorBgElevated
            borderColor = colors.colorSuccess
        }
    }

    Column(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .border(borderWidth, borderColor, shape)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick
                    )
                } else {
                    Modifier
                }
            )
            .padding(resolvedContentPadding),
        verticalArrangement = verticalArrangement,
        content = content
    )
}
