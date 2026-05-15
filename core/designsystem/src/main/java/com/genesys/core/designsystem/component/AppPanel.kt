package com.genesys.core.designsystem.component

import androidx.compose.ui.tooling.preview.Preview
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
import androidx.compose.ui.semantics.Role
import com.genesys.core.designsystem.theme.AppTheme
import androidx.compose.foundation.LocalIndication

enum class AppPanelTone {
    Frame,
    Raised,
    Heavy,
    Error,
    Warning,
    Success
}

@Composable
fun AppPanel(
    modifier: Modifier = Modifier,
    tone: AppPanelTone = AppPanelTone.Raised,
    contentPadding: PaddingValues? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    onClick: (() -> Unit)? = null,
    onClickLabel: String? = null,
    role: Role? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = AppTheme.colorScheme
    val strokes = AppTheme.strokes
    val shape = AppTheme.shapes.shape6
    val resolvedContentPadding = contentPadding ?: PaddingValues(AppTheme.spacing.md)
    val interactionSource = remember { MutableInteractionSource() }
    val backgroundColor: Color
    val borderColor: Color
    val borderWidth = when (tone) {
        AppPanelTone.Frame -> strokes.stroke1
        AppPanelTone.Raised -> strokes.stroke1
        AppPanelTone.Heavy -> strokes.stroke2
        AppPanelTone.Error -> strokes.stroke1
        AppPanelTone.Warning -> strokes.stroke1
        AppPanelTone.Success -> strokes.stroke1
    }

    when (tone) {
        AppPanelTone.Frame -> {
            backgroundColor = colors.colorBgContainer
            borderColor = colors.colorBorderSecondary
        }
        AppPanelTone.Raised -> {
            backgroundColor = colors.colorBgContainer
            borderColor = colors.colorBorder
        }
        AppPanelTone.Heavy -> {
            backgroundColor = colors.colorPrimary
            borderColor = colors.colorPrimary
        }
        AppPanelTone.Error -> {
            backgroundColor = colors.colorBgElevated
            borderColor = colors.colorError
        }
        AppPanelTone.Warning -> {
            backgroundColor = colors.colorBgElevated
            borderColor = colors.colorWarning
        }
        AppPanelTone.Success -> {
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
                        indication = LocalIndication.current,
                        onClickLabel = onClickLabel,
                        role = role,
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

@Preview
@Composable
private fun AppPanelPreview() {
    AppTheme {
        AppPanel {
            AppText("Panel Content")
        }
    }
}
