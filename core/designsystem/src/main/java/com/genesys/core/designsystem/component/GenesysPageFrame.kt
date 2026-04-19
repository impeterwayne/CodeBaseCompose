package com.genesys.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.genesys.core.designsystem.theme.GenesysTheme

@Composable
fun GenesysPageFrame(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val resolvedContentPadding = contentPadding ?: PaddingValues(GenesysTheme.spacing.md)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GenesysTheme.colors.surfaceDim)
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GenesysTheme.colors.surface)
                .border(
                    width = GenesysTheme.strokes.thin,
                    color = GenesysTheme.colors.outlineVariant,
                    shape = GenesysTheme.shapes.large
                )
                .padding(resolvedContentPadding),
            content = content
        )
    }
}
