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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp

@Immutable
data class AppShapes(
    val shape2: Shape,
    val shape4: Shape,
    val shape6: Shape,
    val shape8: Shape,
    val shape12: Shape
)

internal val defaultShapes = AppShapes(
    shape2 = RoundedCornerShape(2.dp),
    shape4 = RoundedCornerShape(14.dp),
    shape6 = RoundedCornerShape(14.dp),
    shape8 = RoundedCornerShape(14.dp),
    shape12 = RoundedCornerShape(14.dp)
)

internal val LocalAppShapes = staticCompositionLocalOf { defaultShapes }

fun Modifier.neoShadow(
    color: Color = Color.Black,
    offsetX: Dp = 2.dp,
    offsetY: Dp = 2.dp,
    shape: Shape
): Modifier = this.drawBehind {
    val outline = shape.createOutline(size, layoutDirection, this)
    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(offsetX.toPx(), offsetY.toPx())
        drawOutline(
            outline = outline,
            color = color
        )
        canvas.restore()
    }
}

object AppTheme {
    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current
    val typography: AppTypography
        @Composable get() = LocalAppTypography.current
    val shapes: AppShapes
        @Composable get() = LocalAppShapes.current
    val spacing: AppSpacing
        @Composable get() = LocalAppSpacing.current
    val strokes: AppStrokes
        @Composable get() = LocalAppStrokes.current
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme()
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as? Activity ?: return@SideEffect
            val window = activity.window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides defaultTypography,
        LocalAppShapes provides defaultShapes,
        LocalAppSpacing provides defaultSpacing,
        LocalAppStrokes provides defaultStrokes,
        content = content
    )
}
