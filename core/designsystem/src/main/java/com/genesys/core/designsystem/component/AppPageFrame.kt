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
import com.genesys.core.designsystem.theme.AppTheme

@Composable
fun AppPageFrame(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val resolvedContentPadding = contentPadding ?: PaddingValues(AppTheme.spacing.md)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.colorBgElevated)
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.colorBgContainer)
                .border(
                    width = AppTheme.strokes.thin,
                    color = AppTheme.colorScheme.colorBorderSecondary,
                    shape = AppTheme.shapes.large
                )
                .padding(resolvedContentPadding),
            content = content
        )
    }
}
