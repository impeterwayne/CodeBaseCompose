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
import androidx.compose.ui.semantics.Role
import com.genesys.core.designsystem.theme.AppTheme
import java.util.Locale

@Composable
fun AppChip(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues? = null,
    onClick: (() -> Unit)? = null,
    onClickLabel: String? = text,
    role: Role? = null
) {
    val colors = AppTheme.colorScheme
    val resolvedContentPadding = contentPadding ?: PaddingValues(
        horizontal = AppTheme.spacing.sm,
        vertical = AppTheme.spacing.xs
    )
    val backgroundColor = if (selected) colors.colorPrimary else colors.colorBgContainer
    val contentColor = if (selected) colors.colorTextOnPrimary else colors.colorPrimary
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .background(backgroundColor)
            .border(AppTheme.strokes.thin, colors.colorPrimary, AppTheme.shapes.small)
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
            text = text.uppercase(Locale.ROOT),
            style = AppTheme.typography.labelSmall,
            color = contentColor
        )
    }
}
