package com.genesys.codebase.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme

private val bottomDestinations = TopLevelDestination.entries

@Composable
fun AppBottomBar(
    currentDestination: TopLevelDestination,
    onDestinationSelected: (TopLevelDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colorScheme.colorBgFill)
            .border(
                width = AppTheme.strokes.thin,
                color = AppTheme.colorScheme.colorBorder,
                shape = AppTheme.shapes.large
            )
            .navigationBarsPadding()
            .padding(
                horizontal = AppTheme.spacing.xs,
                vertical = AppTheme.spacing.sm
            ),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.xs)
    ) {
        bottomDestinations.forEach { destination ->
            BottomBarItem(
                destination = destination,
                selected = currentDestination == destination,
                onClick = { onDestinationSelected(destination) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    destination: TopLevelDestination,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = if (selected) AppTheme.colorScheme.colorPrimary else AppTheme.colorScheme.colorBgContainer
    val contentColor = if (selected) AppTheme.colorScheme.colorTextOnPrimary else AppTheme.colorScheme.colorText

    Column(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(containerColor)
            .border(
                width = AppTheme.strokes.thin,
                color = if (selected) AppTheme.colorScheme.colorPrimary else AppTheme.colorScheme.colorBorderSecondary,
                shape = AppTheme.shapes.medium
            )
            .clickable(onClick = onClick)
            .padding(
                horizontal = AppTheme.spacing.xs,
                vertical = AppTheme.spacing.sm
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.xxs)
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(AppTheme.colorScheme.colorBgContainer)
                .border(
                    width = AppTheme.strokes.thin,
                    color = if (selected) AppTheme.colorScheme.colorBgContainer else AppTheme.colorScheme.colorBorderSecondary,
                    shape = AppTheme.shapes.small
                ),
            contentAlignment = Alignment.Center
        ) {
            AppText(
                text = stringResource(destination.badgeRes),
                style = AppTheme.typography.labelSmall,
                color = AppTheme.colorScheme.colorText
            )
        }

        AppText(
            text = stringResource(destination.labelRes),
            style = AppTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
            color = contentColor
        )
    }
}

@Preview
@Composable
private fun AppBottomBarPreview() {
    AppTheme {
        AppBottomBar(
            currentDestination = bottomDestinations.first(),
            onDestinationSelected = {}
        )
    }
}
