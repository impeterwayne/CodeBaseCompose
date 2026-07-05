package com.genesys.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.theme.AppTheme

enum class GradientDirection {
    TopToBottom, // Fades from solid background color at the top to transparent at the bottom
    BottomToTop  // Fades from transparent at the top to solid background color at the bottom
}

@Composable
fun AppGradientTransition(
    modifier: Modifier = Modifier,
    height: Dp = 16.dp,
    direction: GradientDirection = GradientDirection.TopToBottom,
    color: Color = AppTheme.colorScheme.colorBgLayout
) {
    val colors = when (direction) {
        GradientDirection.TopToBottom -> listOf(color, Color.Transparent)
        GradientDirection.BottomToTop -> listOf(Color.Transparent, color)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
    )
}
