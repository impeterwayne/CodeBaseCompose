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
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.genesys.core.designsystem.theme.AppTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.style.*
import androidx.compose.ui.unit.dp


@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: Style,
    contentPadding: PaddingValues? = null,
    enabled: Boolean = true,
    onClickLabel: String? = text
) {
    val resolvedContentPadding = contentPadding ?: PaddingValues(
        horizontal = AppTheme.spacing.lg,
        vertical = AppTheme.spacing.sm
    )
    val interactionSource = remember { MutableInteractionSource() }
    val styleState = rememberUpdatedStyleState(interactionSource)

    Box(
        modifier = modifier
            .styleable(styleState, style)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClickLabel = onClickLabel,
                role = Role.Button,
                onClick = onClick
            )
            .padding(resolvedContentPadding),
        contentAlignment = Alignment.Center
    ) {
        AppText(
            text = text,
            style = AppTheme.typography.labelLarge.copy(textAlign = TextAlign.Center),
            maxLines = 1,
            modifier = Modifier.basicMarquee()
        )
    }
}

@Composable
fun AppPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues? = null,
    enabled: Boolean = true,
    onClickLabel: String? = text,
    style: Style = rememberNeoButtonStyle(
        backgroundColor = AppTheme.colorScheme.colorPrimary,
        contentColor = AppTheme.colorScheme.colorTextOnPrimary,
        shape = AppTheme.shapes.shape6
    )
) {
    AppButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        style = style,
        contentPadding = contentPadding,
        enabled = enabled,
        onClickLabel = onClickLabel
    )
}

@Composable
fun AppSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues? = null,
    enabled: Boolean = true,
    onClickLabel: String? = text,
    style: Style = rememberNeoButtonStyle(
        backgroundColor = AppTheme.colorScheme.colorBgContainer,
        contentColor = AppTheme.colorScheme.neoText,
        shape = AppTheme.shapes.shape6
    )
) {
    AppButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        style = style,
        contentPadding = contentPadding,
        enabled = enabled,
        onClickLabel = onClickLabel
    )
}

@Composable
fun AppWarningButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues? = null,
    enabled: Boolean = true,
    onClickLabel: String? = text,
    style: Style = rememberNeoButtonStyle(
        backgroundColor = AppTheme.colorScheme.colorError,
        contentColor = AppTheme.colorScheme.colorTextOnError,
        shape = AppTheme.shapes.shape6
    )
) {
    AppButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        style = style,
        contentPadding = contentPadding,
        enabled = enabled,
        onClickLabel = onClickLabel
    )
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

@Preview
@Composable
private fun AppWarningButtonPreview() {
    AppTheme {
        AppWarningButton(text = "Warning Button", onClick = {})
    }
}
