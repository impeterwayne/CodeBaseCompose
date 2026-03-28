package com.genesys.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val PaperWhite = Color(0xFFF9F9F9)
private val AbsoluteWhite = Color(0xFFFFFFFF)
private val AbsoluteBlack = Color(0xFF000000)
private val PaperFrame = Color(0xFFDADADA)
private val SurfaceContainerLow = Color(0xFFF3F3F4)
private val SurfaceContainer = Color(0xFFEEEEEE)
private val SurfaceContainerHighest = Color(0xFFE2E2E2)
private val InkBlock = Color(0xFF3B3B3B)
private val Outline = Color(0xFF777777)
private val OutlineVariant = Color(0xFFC6C6C6)
private val EditorialInk = Color(0xFF1A1C1C)

@Immutable
data class GenesysColors(
    val background: Color,
    val surface: Color,
    val surfaceDim: Color,
    val primary: Color,
    val onSurface: Color,
    val onPrimary: Color,
    val surfaceContainerLowest: Color,
    val surfaceContainerLow: Color,
    val surfaceContainer: Color,
    val surfaceContainerHighest: Color,
    val outline: Color,
    val outlineVariant: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color
)

internal val lightColors = GenesysColors(
    background = PaperWhite,
    surface = PaperWhite,
    surfaceDim = PaperFrame,
    primary = AbsoluteBlack,
    onSurface = EditorialInk,
    onPrimary = AbsoluteWhite,
    surfaceContainerLowest = AbsoluteWhite,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainer = SurfaceContainer,
    surfaceContainerHighest = SurfaceContainerHighest,
    outline = Outline,
    outlineVariant = OutlineVariant,
    primaryContainer = InkBlock,
    onPrimaryContainer = AbsoluteWhite
)

internal val darkColors = GenesysColors(
    background = EditorialInk,
    surface = EditorialInk,
    surfaceDim = AbsoluteBlack,
    primary = AbsoluteWhite,
    onSurface = PaperWhite,
    onPrimary = AbsoluteBlack,
    surfaceContainerLowest = AbsoluteBlack,
    surfaceContainerLow = Color(0xFF121212),
    surfaceContainer = Color(0xFF222222),
    surfaceContainerHighest = InkBlock,
    outline = OutlineVariant,
    outlineVariant = InkBlock,
    primaryContainer = SurfaceContainerHighest,
    onPrimaryContainer = AbsoluteBlack
)

internal val LocalGenesysColors = staticCompositionLocalOf { lightColors }
