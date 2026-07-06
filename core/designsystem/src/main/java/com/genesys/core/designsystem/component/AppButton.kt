@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.genesys.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.genesys.core.designsystem.theme.AppTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.style.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.res.painterResource
import com.genesys.core.designsystem.R

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int? = null,
    iconTint: Color? = null,
    iconSize: Dp = 20.dp,
    style: Style,
    contentPadding: PaddingValues? = null,
    enabled: Boolean = true,
    onClickLabel: String? = text.ifEmpty { null }
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (iconResId != null) {
                val colorFilter = if (iconTint != null) ColorFilter.tint(iconTint) else null
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    colorFilter = colorFilter,
                    modifier = Modifier.size(iconSize)
                )
                if (text.isNotEmpty()) {
                    Spacer(modifier = Modifier.width(AppTheme.spacing.xs))
                }
            }
            if (text.isNotEmpty()) {
                AppText(
                    text = text,
                    style = AppTheme.typography.labelLarge.copy(textAlign = TextAlign.Center),
                    maxLines = 1,
                    modifier = Modifier.basicMarquee()
                )
            }
        }
    }
}

@Composable
fun AppPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int? = null,
    iconTint: Color? = AppTheme.colorScheme.colorTextOnPrimary,
    iconSize: Dp = 20.dp,
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
        iconResId = iconResId,
        iconTint = iconTint,
        iconSize = iconSize,
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
    @DrawableRes iconResId: Int? = null,
    iconTint: Color? = AppTheme.colorScheme.neoText,
    iconSize: Dp = 20.dp,
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
        iconResId = iconResId,
        iconTint = iconTint,
        iconSize = iconSize,
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
    @DrawableRes iconResId: Int? = null,
    iconTint: Color? = AppTheme.colorScheme.colorTextOnError,
    iconSize: Dp = 20.dp,
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
        iconResId = iconResId,
        iconTint = iconTint,
        iconSize = iconSize,
        style = style,
        contentPadding = contentPadding,
        enabled = enabled,
        onClickLabel = onClickLabel
    )
}

@Composable
fun AppCircleButton(
    @DrawableRes iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconTint: Color? = null,
    iconSize: Dp = 24.dp,
    style: Style,
    contentPadding: PaddingValues? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null
) {
    AppButton(
        text = "",
        onClick = onClick,
        modifier = modifier,
        iconResId = iconResId,
        iconTint = iconTint,
        iconSize = iconSize,
        style = style,
        contentPadding = contentPadding ?: PaddingValues(AppTheme.spacing.sm),
        enabled = enabled,
        onClickLabel = onClickLabel
    )
}

@Composable
fun AppPrimaryCircleButton(
    @DrawableRes iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconTint: Color? = AppTheme.colorScheme.colorTextOnPrimary,
    iconSize: Dp = 24.dp,
    contentPadding: PaddingValues? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    style: Style = rememberNeoButtonStyle(
        backgroundColor = AppTheme.colorScheme.colorPrimary,
        contentColor = AppTheme.colorScheme.colorTextOnPrimary,
        shape = CircleShape
    )
) {
    AppCircleButton(
        iconResId = iconResId,
        onClick = onClick,
        modifier = modifier,
        iconTint = iconTint,
        iconSize = iconSize,
        style = style,
        contentPadding = contentPadding,
        enabled = enabled,
        onClickLabel = onClickLabel
    )
}

@Composable
fun AppSecondaryCircleButton(
    @DrawableRes iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconTint: Color? = AppTheme.colorScheme.neoText,
    iconSize: Dp = 24.dp,
    contentPadding: PaddingValues? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    style: Style = rememberNeoButtonStyle(
        backgroundColor = AppTheme.colorScheme.colorBgContainer,
        contentColor = AppTheme.colorScheme.neoText,
        shape = CircleShape
    )
) {
    AppCircleButton(
        iconResId = iconResId,
        onClick = onClick,
        modifier = modifier,
        iconTint = iconTint,
        iconSize = iconSize,
        style = style,
        contentPadding = contentPadding,
        enabled = enabled,
        onClickLabel = onClickLabel
    )
}

@Composable
fun AppWarningCircleButton(
    @DrawableRes iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconTint: Color? = AppTheme.colorScheme.colorTextOnError,
    iconSize: Dp = 24.dp,
    contentPadding: PaddingValues? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    style: Style = rememberNeoButtonStyle(
        backgroundColor = AppTheme.colorScheme.colorError,
        contentColor = AppTheme.colorScheme.colorTextOnError,
        shape = CircleShape
    )
) {
    AppCircleButton(
        iconResId = iconResId,
        onClick = onClick,
        modifier = modifier,
        iconTint = iconTint,
        iconSize = iconSize,
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
private fun AppPrimaryButtonWithIconPreview() {
    AppTheme {
        AppPrimaryButton(
            text = "Primary Button",
            onClick = {},
            iconResId = R.drawable.ic_arrow_back
        )
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

@Preview
@Composable
private fun AppCircleButtonPreview() {
    AppTheme {
        AppPrimaryCircleButton(
            onClick = {},
            iconResId = R.drawable.ic_arrow_back
        )
    }
}
