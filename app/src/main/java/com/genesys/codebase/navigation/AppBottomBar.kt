package com.genesys.codebase.navigation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.genesys.codebase.R
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.designsystem.theme.neoShadow

@Composable
fun AppBottomBar(
    currentDestination: TopLevelDestination,
    onDestinationSelected: (TopLevelDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    AppBottomBar(
        currentDestination = currentDestination,
        onDestinationSelected = onDestinationSelected,
        onPrimarySelected = {
            Toast.makeText(
                context,
                context.getString(R.string.nav_primary) + " Clicked!",
                Toast.LENGTH_SHORT
            ).show()
        },
        modifier = modifier
    )
}

@Composable
fun AppBottomBar(
    currentDestination: TopLevelDestination,
    onDestinationSelected: (TopLevelDestination) -> Unit,
    onPrimarySelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    val barShape = RoundedCornerShape(20.dp)
    val neoYellow = colorResource(id = com.genesys.core.designsystem.R.color.neo_primary_yellow)
    val neoOrange = colorResource(id = com.genesys.core.designsystem.R.color.neo_selected_orange)
    val outlineColor = Color.Black

    Box(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(
                horizontal = AppTheme.spacing.md,
                vertical = AppTheme.spacing.sm
            )
    ) {
        // The main bottom bar container
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp) // Offset down to let the central button protrude
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Items
            BottomTabItem(
                destination = TopLevelDestination.Pokedex,
                selected = currentDestination == TopLevelDestination.Pokedex,
                onClick = { onDestinationSelected(TopLevelDestination.Pokedex) },
                activeColor = neoOrange,
                inactiveColor = outlineColor,
                modifier = Modifier.weight(1f)
            )

            BottomTabItem(
                destination = TopLevelDestination.Feature1,
                selected = currentDestination == TopLevelDestination.Feature1,
                onClick = { onDestinationSelected(TopLevelDestination.Feature1) },
                activeColor = neoOrange,
                inactiveColor = outlineColor,
                modifier = Modifier.weight(1f)
            )

            // Middle Placeholder Item (Primary)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 28.dp), // Push text down below the yellow circle
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AppText(
                    text = stringResource(id = R.string.nav_primary),
                    style = AppTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    color = outlineColor
                )
            }

            // Right Items
            BottomTabItem(
                destination = TopLevelDestination.Feature2,
                selected = currentDestination == TopLevelDestination.Feature2,
                onClick = { onDestinationSelected(TopLevelDestination.Feature2) },
                activeColor = neoOrange,
                inactiveColor = outlineColor,
                modifier = Modifier.weight(1f)
            )

            BottomTabItem(
                destination = TopLevelDestination.Feature3,
                selected = currentDestination == TopLevelDestination.Feature3,
                onClick = { onDestinationSelected(TopLevelDestination.Feature3) },
                activeColor = neoOrange,
                inactiveColor = outlineColor,
                modifier = Modifier.weight(1f)
            )
        }

        // Floating Central Button (Primary Action)
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-4).dp) // Fine-tune vertical overlap
                .size(60.dp)
                .neoShadow(color = outlineColor, offsetX = 4.dp, offsetY = 4.dp, shape = CircleShape)
                .background(neoYellow, CircleShape)
                .border(
                    width = 2.5.dp,
                    color = outlineColor,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .clickable(onClick = onPrimarySelected),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_nav_primary),
                contentDescription = stringResource(id = R.string.nav_primary),
                colorFilter = ColorFilter.tint(outlineColor),
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
private fun BottomTabItem(
    destination: TopLevelDestination,
    selected: Boolean,
    onClick: () -> Unit,
    activeColor: Color,
    inactiveColor: Color,
    modifier: Modifier = Modifier
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
            painter = painterResource(id = destination.iconRes),
            contentDescription = stringResource(id = destination.labelRes),
            colorFilter = ColorFilter.tint(tintColor),
            modifier = Modifier.size(26.dp)
        )

        AppText(
            text = stringResource(id = destination.labelRes),
            style = AppTheme.typography.labelMedium.copy(
                fontWeight = fontWeight,
                textAlign = TextAlign.Center
            ),
            color = tintColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppBottomBarPreview() {
    AppTheme {
        AppBottomBar(
            currentDestination = TopLevelDestination.Pokedex,
            onDestinationSelected = {}
        )
    }
}
