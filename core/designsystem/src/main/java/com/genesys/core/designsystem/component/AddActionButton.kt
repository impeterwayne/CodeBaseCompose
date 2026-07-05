package com.genesys.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.R
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.designsystem.theme.neoShadow

@Composable
fun AddActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    val neoYellow = colorResource(id = R.color.neo_primary_yellow)
    val outlineColor = Color.Black

    Box(
        modifier = modifier
            .navigationBarsPadding()
            .padding(bottom = AppTheme.spacing.sm)
            .offset(y = (-20).dp) // Fine-tune vertical overlap to protrude from the bottom bar
            .size(60.dp)
            .neoShadow(color = outlineColor, offsetX = 4.dp, offsetY = 4.dp, shape = CircleShape)
            .background(neoYellow, CircleShape)
            .border(
                width = 2.5.dp,
                color = outlineColor,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_nav_primary),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(outlineColor),
            modifier = Modifier.size(28.dp)
        )
    }
}
