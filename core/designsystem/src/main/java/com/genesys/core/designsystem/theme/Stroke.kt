package com.genesys.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class GenesysStrokes(
    val thin: Dp,
    val medium: Dp,
    val thick: Dp
)

internal val defaultStrokes = GenesysStrokes(
    thin = 1.dp,
    medium = 2.dp,
    thick = 3.dp
)

internal val LocalGenesysStrokes = staticCompositionLocalOf { defaultStrokes }
