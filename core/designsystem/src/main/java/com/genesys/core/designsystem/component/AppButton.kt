package com.genesys.core.designsystem.component

import androidx.compose.ui.tooling.preview.Preview
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
import androidx.compose.ui.semantics.Role
import com.genesys.core.designsystem.theme.AppTheme
import androidx.compose.ui.graphics.Color
import com.genesys.core.designsystem.theme.neoShadow
import androidx.compose.foundation.LocalIndication

@Composable
fun AppPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues? = null,
    enabled: Boolean = true,
    onClickLabel: String? = text
) {
    val colors = AppTheme.colorScheme
    val resolvedContentPadding = contentPadding ?: PaddingValues(
        horizontal = AppTheme.spacing.lg,
        vertical = AppTheme.spacing.sm
    )
    val interactionSource = remember { MutableInteractionSource() }
    val shape = AppTheme.shapes.shape6

    Box(
        modifier = modifier
            .neoShadow(color = Color.Black, shape = shape)
            .background(colors.colorPrimary, shape)
            .border(
                width = AppTheme.strokes.stroke2,
                color = Color.Black,
                shape = shape
            )
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                enabled = enabled,
                onClickLabel = onClickLabel,
                role = Role.Button,
                onClick = onClick
            )
            .padding(resolvedContentPadding)
    ) {
        AppText(
            text = text,
            style = AppTheme.typography.labelLarge,
            color = colors.colorTextOnPrimary
        )
    }
}

@Composable
fun AppSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues? = null,
    enabled: Boolean = true,
    onClickLabel: String? = text
) {
    val colors = AppTheme.colorScheme
    val resolvedContentPadding = contentPadding ?: PaddingValues(
        horizontal = AppTheme.spacing.lg,
        vertical = AppTheme.spacing.sm
    )
    val interactionSource = remember { MutableInteractionSource() }
    val shape = AppTheme.shapes.shape6

    Box(
        modifier = modifier
            .neoShadow(color = Color.Black, shape = shape)
            .background(colors.colorBgContainer, shape)
            .border(
                width = AppTheme.strokes.stroke2,
                color = Color.Black,
                shape = shape
            )
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                enabled = enabled,
                onClickLabel = onClickLabel,
                role = Role.Button,
                onClick = onClick
            )
            .padding(resolvedContentPadding)
    ) {
        AppText(
            text = text,
            style = AppTheme.typography.labelLarge,
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun AppPrimaryButtonPreview() {
    AppTheme {
        AppPrimaryButton(text = "Primary Button", onClick = {})
    }
}

@Preview
@Composable
private fun AppSecondaryButtonPreview() {
    AppTheme {
        AppSecondaryButton(text = "Secondary Button", onClick = {})
    }
}
