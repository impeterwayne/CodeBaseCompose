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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.graphics.Color
import com.genesys.core.designsystem.theme.neoShadow
import androidx.compose.ui.draw.clip
import com.genesys.core.designsystem.theme.AppTheme
import androidx.compose.foundation.LocalIndication

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
    val contentColor = if (selected) colors.colorTextOnPrimary else Color.Black
    val interactionSource = remember { MutableInteractionSource() }
    val shape = AppTheme.shapes.shape4

    Box(
        modifier = modifier
            .neoShadow(color = Color.Black, shape = shape)
            .background(backgroundColor, shape)
            .border(AppTheme.strokes.stroke2, Color.Black, shape)
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
            .padding(resolvedContentPadding)
    ) {
        AppText(
            text = text,
            style = AppTheme.typography.labelSmall,
            color = contentColor
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
