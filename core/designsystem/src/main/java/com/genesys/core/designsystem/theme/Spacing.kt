package com.genesys.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class GenesysSpacing(
    val xxs: Dp,
    val xs: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp,
    val xxl: Dp
)

internal val defaultSpacing = GenesysSpacing(
    xxs = 4.dp,
    xs = 8.dp,
    sm = 12.dp,
    md = 16.dp,
    lg = 24.dp,
    xl = 32.dp,
    xxl = 40.dp
)

internal val LocalGenesysSpacing = staticCompositionLocalOf { defaultSpacing }
