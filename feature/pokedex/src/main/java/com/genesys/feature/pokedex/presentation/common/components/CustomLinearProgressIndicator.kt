package com.genesys.feature.pokedex.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.theme.AppTheme

@Composable
fun CustomLinearProgressIndicator(
    progress: Float,
    color: Color,
    trackColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(trackColor, shape = RoundedCornerShape(5.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .background(color, shape = RoundedCornerShape(5.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomLinearProgressIndicatorPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            CustomLinearProgressIndicator(
                progress = 0.7f,
                color = Color(0xFF4CAF50),
                trackColor = Color.LightGray.copy(alpha = 0.2f),
                modifier = Modifier
                    .width(200.dp)
                    .height(10.dp)
            )
        }
    }
}
