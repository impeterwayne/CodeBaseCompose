package com.genesys.core.designsystem.component

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.genesys.core.designsystem.theme.GenesysTheme

@Composable
fun GenesysText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle? = null,
    color: Color? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    val resolvedStyle = style ?: GenesysTheme.typography.bodyLarge
    val resolvedColor = color ?: GenesysTheme.colors.onSurface

    BasicText(
        text = text,
        modifier = modifier,
        style = resolvedStyle.copy(color = resolvedColor),
        maxLines = maxLines,
        overflow = overflow
    )
}
