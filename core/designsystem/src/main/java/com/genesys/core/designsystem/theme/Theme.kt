package com.genesys.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

@Immutable
data class GenesysShapes(
    val extraSmall: Shape,
    val small: Shape,
    val medium: Shape,
    val large: Shape,
    val extraLarge: Shape
)

internal val defaultShapes = GenesysShapes(
    extraSmall = RoundedCornerShape(0.dp),
    small = RoundedCornerShape(0.dp),
    medium = RoundedCornerShape(0.dp),
    large = RoundedCornerShape(0.dp),
    extraLarge = RoundedCornerShape(0.dp)
)

internal val LocalGenesysShapes = staticCompositionLocalOf { defaultShapes }

object GenesysTheme {
    val colors: GenesysColors
        @Composable get() = LocalGenesysColors.current
    val typography: GenesysTypography
        @Composable get() = LocalGenesysTypography.current
    val shapes: GenesysShapes
        @Composable get() = LocalGenesysShapes.current
    val spacing: GenesysSpacing
        @Composable get() = LocalGenesysSpacing.current
    val strokes: GenesysStrokes
        @Composable get() = LocalGenesysStrokes.current
}

@Composable
fun GenesysTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColors else lightColors
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as? Activity ?: return@SideEffect
            val window = activity.window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalGenesysColors provides colors,
        LocalGenesysTypography provides defaultTypography,
        LocalGenesysShapes provides defaultShapes,
        LocalGenesysSpacing provides defaultSpacing,
        LocalGenesysStrokes provides defaultStrokes,
        content = content
    )
}
