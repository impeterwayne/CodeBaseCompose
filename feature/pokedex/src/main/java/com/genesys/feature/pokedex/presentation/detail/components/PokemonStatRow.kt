package com.genesys.feature.pokedex.presentation.detail.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.feature.pokedex.presentation.common.components.CustomLinearProgressIndicator

@Composable
fun PokemonStatRow(
    name: String,
    value: Int,
    max: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(
            text = name,
            style = AppTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color = AppTheme.colorScheme.colorText,
            modifier = Modifier.width(90.dp)
        )

        AppText(
            text = value.toString(),
            style = AppTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = AppTheme.colorScheme.colorText,
            modifier = Modifier.width(40.dp)
        )

        Spacer(modifier = Modifier.width(AppTheme.spacing.xs))

        val animatedProgress = animateFloatAsState(
            targetValue = (value.toFloat() / max.toFloat()).coerceIn(0f, 1f),
            animationSpec = tween(durationMillis = 800),
            label = name
        )

        CustomLinearProgressIndicator(
            progress = animatedProgress.value,
            color = color,
            trackColor = AppTheme.colorScheme.colorBorder.copy(alpha = 0.15f),
            modifier = Modifier
                .weight(1f)
                .height(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonStatRowPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            PokemonStatRow(
                name = "HP",
                value = 45,
                max = 150,
                color = AppTheme.colorScheme.colorPrimary
            )
        }
    }
}
