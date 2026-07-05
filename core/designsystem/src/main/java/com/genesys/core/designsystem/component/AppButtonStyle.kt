@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.genesys.core.designsystem.component

import androidx.compose.foundation.style.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.theme.AppTheme



@OptIn(ExperimentalFoundationStyleApi::class)
fun createNeoButtonStyle(
    backgroundColor: Color,
    contentColor: Color,
    shape: Shape,
    borderWidth: Dp = 2.dp,
    shadowOffset: Dp = 2.dp,
    pressedTranslation: Float = 2f,
    borderColor: Color = Color.Black,
    shadowColor: Color = Color.Black
): Style = Style {
    background(backgroundColor)
    contentColor(contentColor)
    shape(shape)
    border(borderWidth, borderColor)
    translation(0f, 0f)
    dropShadow(
        Shadow(
            color = shadowColor,
            offset = DpOffset(shadowOffset, shadowOffset),
            radius = 0.dp,
            spread = 0.dp
        )
    )
    pressed(Style {
        animate(Style {
            translation(pressedTranslation, pressedTranslation)
            dropShadow(
                Shadow(
                    color = shadowColor,
                    offset = DpOffset(0.dp, 0.dp),
                    radius = 0.dp,
                    spread = 0.dp
                )
            )
        })
    })
}

@Composable
fun rememberNeoButtonStyle(
    backgroundColor: Color,
    contentColor: Color,
    shape: Shape,
    borderWidth: Dp = 2.dp,
    shadowOffset: Dp = 2.dp,
    pressedTranslation: Float = 2f,
    borderColor: Color = AppTheme.colorScheme.neoBorder,
    shadowColor: Color = AppTheme.colorScheme.neoShadow
): Style {
    return remember(
        backgroundColor, contentColor, shape, borderWidth, shadowOffset,
        pressedTranslation, borderColor, shadowColor
    ) {
        createNeoButtonStyle(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            shape = shape,
            borderWidth = borderWidth,
            shadowOffset = shadowOffset,
            pressedTranslation = pressedTranslation,
            borderColor = borderColor,
            shadowColor = shadowColor
        )
    }
}
