@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.genesys.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.R
import com.genesys.core.designsystem.theme.AppTheme
import androidx.compose.foundation.style.*


@Composable
fun defaultAddActionButtonStyle(): Style = rememberNeoButtonStyle(
    backgroundColor = colorResource(id = R.color.neo_action_button_bg),
    contentColor = AppTheme.colorScheme.neoText,
    shape = CircleShape,
    borderWidth = 2.5.dp,
    shadowOffset = 4.dp,
    pressedTranslation = 4f
)


@Composable
fun AddActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: Style = defaultAddActionButtonStyle(),
    contentDescription: String? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val styleState = rememberUpdatedStyleState(interactionSource)

    Box(
        modifier = modifier
            .navigationBarsPadding()
            .padding(bottom = AppTheme.spacing.sm)
            .offset(y = (-20).dp) // protrude from bottom bar
            .size(60.dp)
            .styleable(styleState, style)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_nav_primary),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(AppTheme.colorScheme.neoText),
            modifier = Modifier.size(28.dp)
        )
    }
}
