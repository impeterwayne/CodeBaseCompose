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
import com.genesys.core.designsystem.theme.neoShadow
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
    val borderColor = Color.Black
    val borderWidth = strokes.stroke2

    when (tone) {
        AppPanelTone.Frame -> {
            backgroundColor = colors.colorBgContainer
        }
        AppPanelTone.Raised -> {
            backgroundColor = colors.colorBgContainer
        }
        AppPanelTone.Heavy -> {
            backgroundColor = colors.colorPrimary
        }
        AppPanelTone.Error -> {
            backgroundColor = colors.colorBgElevated
        }
        AppPanelTone.Warning -> {
            backgroundColor = colors.colorBgElevated
        }
        AppPanelTone.Success -> {
            backgroundColor = colors.colorBgElevated
        }
    }

    Column(
        modifier = modifier
            .neoShadow(color = Color.Black, shape = shape)
            .background(backgroundColor, shape)
            .border(borderWidth, borderColor, shape)
            .clip(shape)
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
