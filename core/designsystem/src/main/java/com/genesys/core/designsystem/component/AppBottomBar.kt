package com.genesys.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.R
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.designsystem.theme.neoShadow

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val barShape = RoundedCornerShape(20.dp)
    val outlineColor = Color.Black

    Row(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(
                horizontal = AppTheme.spacing.md,
                vertical = AppTheme.spacing.sm
            )
            .neoShadow(color = outlineColor, offsetX = 4.dp, offsetY = 4.dp, shape = barShape)
            .background(AppTheme.colorScheme.colorBgContainer, barShape)
            .border(
                width = 2.dp,
                color = outlineColor,
                shape = barShape
            )
            .clip(barShape)
            .padding(vertical = AppTheme.spacing.xs),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
fun AppBottomTabItem(
    painter: Painter,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    activeColor: Color = colorResource(id = R.color.neo_selected_orange),
    inactiveColor: Color = Color.Black
) {
    val tintColor = if (selected) activeColor else inactiveColor
    val fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(vertical = AppTheme.spacing.xxs),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = label,
            colorFilter = ColorFilter.tint(tintColor),
            modifier = Modifier.size(26.dp)
        )

        AppText(
            text = label,
            style = AppTheme.typography.labelMedium.copy(
                fontWeight = fontWeight,
                textAlign = TextAlign.Center
            ),
            color = tintColor
        )
    }
}
