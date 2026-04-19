package com.genesys.codebase.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.genesys.codebase.R
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.core.navigation.GenesysNavigatorImpl
import com.genesys.core.navigation.GenesysScreen
import com.genesys.feature.inbox.navigation.InboxRoute
import com.genesys.feature.projects.navigation.ProjectsRoute
import com.genesys.feature.settings.navigation.SettingsRoute
import com.genesys.feature.template.navigation.TemplateRoute
import androidx.compose.ui.text.style.TextAlign

import androidx.annotation.StringRes

private enum class AppDestination(
    val screen: GenesysScreen,
    @StringRes val labelRes: Int,
    @StringRes val badgeRes: Int
) {
    Templates(screen = GenesysScreen.Templates, labelRes = R.string.nav_templates, badgeRes = R.string.nav_badge_templates),
    Projects(screen = GenesysScreen.Projects, labelRes = R.string.nav_projects, badgeRes = R.string.nav_badge_projects),
    Inbox(screen = GenesysScreen.Inbox, labelRes = R.string.nav_inbox, badgeRes = R.string.nav_badge_inbox),
    Settings(screen = GenesysScreen.Settings, labelRes = R.string.nav_settings, badgeRes = R.string.nav_badge_settings)
}

private val bottomDestinations = AppDestination.entries

@Composable
fun AppShell(
    modifier: Modifier = Modifier
) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestination.Templates) }
    val templateBackStack: NavBackStack<NavKey> = rememberNavBackStack(GenesysScreen.Templates)
    val projectsBackStack: NavBackStack<NavKey> = rememberNavBackStack(GenesysScreen.Projects)
    val inboxBackStack: NavBackStack<NavKey> = rememberNavBackStack(GenesysScreen.Inbox)
    val settingsBackStack: NavBackStack<NavKey> = rememberNavBackStack(GenesysScreen.Settings)
    val templateNavigator = androidx.compose.runtime.remember(templateBackStack) { GenesysNavigatorImpl(templateBackStack) }
    val activeBackStack: NavBackStack<NavKey> = when (currentDestination) {
        AppDestination.Templates -> templateBackStack
        AppDestination.Projects -> projectsBackStack
        AppDestination.Inbox -> inboxBackStack
        AppDestination.Settings -> settingsBackStack
    }
    val activeRootKey: NavKey = currentDestination.screen

    BackHandler(enabled = activeBackStack.last() != activeRootKey) {
        activeBackStack.removeLastOrNull()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GenesysTheme.colors.surfaceDim)
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            when (currentDestination) {
                AppDestination.Templates -> TemplateRoute(
                    backStack = templateBackStack,
                    navigator = templateNavigator,
                    modifier = Modifier.fillMaxSize()
                )
                AppDestination.Projects -> ProjectsRoute(
                    modifier = Modifier.fillMaxSize()
                )
                AppDestination.Inbox -> InboxRoute(
                    modifier = Modifier.fillMaxSize()
                )
                AppDestination.Settings -> SettingsRoute(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        AppBottomBar(
            currentDestination = currentDestination,
            onDestinationSelected = { destination ->
                if (currentDestination != destination) {
                    currentDestination = destination
                }
            }
        )
    }
}

@Composable
private fun AppBottomBar(
    currentDestination: AppDestination,
    onDestinationSelected: (AppDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(GenesysTheme.colors.surfaceContainerHighest)
            .border(
                width = GenesysTheme.strokes.thin,
                color = GenesysTheme.colors.outline,
                shape = GenesysTheme.shapes.large
            )
            .navigationBarsPadding()
            .padding(
                horizontal = GenesysTheme.spacing.xs,
                vertical = GenesysTheme.spacing.sm
            ),
        horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
    ) {
        bottomDestinations.forEach { destination ->
            val selected = (currentDestination == destination)

            BottomBarItem(
                destination = destination,
                selected = selected,
                onClick = { onDestinationSelected(destination) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    destination: AppDestination,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = if (selected) {
        GenesysTheme.colors.primaryContainer
    } else {
        GenesysTheme.colors.surface
    }
    val contentColor = if (selected) {
        GenesysTheme.colors.onPrimaryContainer
    } else {
        GenesysTheme.colors.onSurface
    }
    val badgeColor = if (selected) {
        GenesysTheme.colors.surface
    } else {
        GenesysTheme.colors.surfaceContainerLow
    }

    Column(
        modifier = modifier
            .clip(GenesysTheme.shapes.medium)
            .background(containerColor)
            .border(
                width = GenesysTheme.strokes.thin,
                color = if (selected) GenesysTheme.colors.primary else GenesysTheme.colors.outlineVariant,
                shape = GenesysTheme.shapes.medium
            )
            .clickable(onClick = onClick)
            .padding(
                horizontal = GenesysTheme.spacing.xs,
                vertical = GenesysTheme.spacing.sm
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xxs)
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(badgeColor)
                .border(
                    width = GenesysTheme.strokes.thin,
                    color = if (selected) GenesysTheme.colors.surface else GenesysTheme.colors.outlineVariant,
                    shape = GenesysTheme.shapes.small
                ),
            contentAlignment = Alignment.Center
        ) {
            GenesysText(
                text = stringResource(destination.badgeRes),
                style = GenesysTheme.typography.labelSmall,
                color = GenesysTheme.colors.onSurface
            )
        }

        GenesysText(
            text = stringResource(destination.labelRes),
            style = GenesysTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
            color = contentColor
        )
    }
}
