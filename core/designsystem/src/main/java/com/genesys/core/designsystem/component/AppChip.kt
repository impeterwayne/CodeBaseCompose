@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.genesys.core.designsystem.component

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.graphics.Color
import com.genesys.core.designsystem.theme.AppTheme
import androidx.compose.foundation.style.*
import androidx.compose.ui.unit.dp

@Composable
fun defaultChipStyle(selected: Boolean): Style {
    val colors = AppTheme.colorScheme
    return rememberNeoButtonStyle(
        backgroundColor = if (selected) colors.colorPrimary else colors.colorBgContainer,
        contentColor = if (selected) colors.colorTextOnPrimary else colors.neoText,
        shape = AppTheme.shapes.shape4
    )
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun AppChip(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    style: Style = defaultChipStyle(selected),
    contentPadding: PaddingValues? = null,
    onClick: (() -> Unit)? = null,
    onClickLabel: String? = text,
    role: Role? = null
) {
    val resolvedContentPadding = contentPadding ?: PaddingValues(
        horizontal = AppTheme.spacing.sm,
        vertical = AppTheme.spacing.xs
    )
    val interactionSource = remember { MutableInteractionSource() }
    val styleState = rememberUpdatedStyleState(interactionSource)

    Box(
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
            .padding(resolvedContentPadding)
    ) {
        AppText(
            text = text,
            style = AppTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
private fun AppChipPreview() {
    AppTheme {
        AppChip(text = "Selected Chip", selected = true, onClick = {})
    }
}

@Preview
@Composable
private fun AppChipUnselectedPreview() {
    AppTheme {
        AppChip(text = "Unselected Chip", selected = false, onClick = {})
    }
}
