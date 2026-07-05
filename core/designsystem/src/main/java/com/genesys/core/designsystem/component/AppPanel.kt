@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.genesys.core.designsystem.component

import androidx.compose.ui.tooling.preview.Preview
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import com.genesys.core.designsystem.theme.AppTheme
import androidx.compose.foundation.style.*
import androidx.compose.ui.unit.dp

enum class AppPanelTone {
    Frame,
    Raised,
    Heavy,
    Error,
    Warning,
    Success
}

@Composable
fun defaultPanelStyle(tone: AppPanelTone): Style {
    val colors = AppTheme.colorScheme
    val backgroundColor = when (tone) {
        AppPanelTone.Frame -> colors.colorBgContainer
        AppPanelTone.Raised -> colors.colorBgContainer
        AppPanelTone.Heavy -> colors.colorPrimary
        AppPanelTone.Error -> colors.colorBgElevated
        AppPanelTone.Warning -> colors.colorBgElevated
        AppPanelTone.Success -> colors.colorBgElevated
    }
    return rememberNeoButtonStyle(
        backgroundColor = backgroundColor,
        contentColor = colors.neoText,
        shape = AppTheme.shapes.shape6
    )
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun AppPanel(
    modifier: Modifier = Modifier,
    tone: AppPanelTone = AppPanelTone.Raised,
    style: Style = defaultPanelStyle(tone),
    contentPadding: PaddingValues? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    onClick: (() -> Unit)? = null,
    onClickLabel: String? = null,
    role: Role? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val resolvedContentPadding = contentPadding ?: PaddingValues(AppTheme.spacing.md)
    val interactionSource = remember { MutableInteractionSource() }
    val styleState = rememberUpdatedStyleState(interactionSource)

    Column(
        modifier = modifier
            .styleable(styleState, style)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
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
