package com.genesys.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AppStrokes(
    val stroke1: Dp,
    val stroke2: Dp,
    val stroke3: Dp
)

internal val defaultStrokes = AppStrokes(
    stroke1 = 1.dp,
    stroke2 = 2.dp,
    stroke3 = 3.dp
)

internal val LocalAppStrokes = staticCompositionLocalOf { defaultStrokes }
