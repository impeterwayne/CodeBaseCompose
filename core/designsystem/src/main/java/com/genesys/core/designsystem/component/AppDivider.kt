package com.genesys.core.designsystem.component

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.genesys.core.designsystem.theme.AppTheme

@Composable
fun AppDivider(
    modifier: Modifier = Modifier,
    color: Color? = null,
    thickness: Dp? = null
) {
    val resolvedColor = color ?: AppTheme.colorScheme.colorBorderSecondary
    val resolvedThickness = thickness ?: AppTheme.strokes.stroke1

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(resolvedThickness)
            .background(resolvedColor)
    )
}

@Preview
@Composable
private fun AppDividerPreview() {
    AppTheme {
        AppDivider()
    }
}
