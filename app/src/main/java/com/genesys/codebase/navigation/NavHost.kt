package com.genesys.codebase.navigation

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.genesys.codebase.R
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.navigation.AppNavigator
import com.genesys.core.navigation.AppNavigatorImpl
import com.genesys.core.navigation.Route
import com.genesys.feature.inbox.navigation.InboxGraph
import com.genesys.feature.projects.navigation.ProjectsGraph
import com.genesys.feature.settings.navigation.SettingsGraph
import com.genesys.feature.template.navigation.TemplateGraph

enum class TopLevelDestination(
    val screen: Route,
    @StringRes val labelRes: Int,
    @StringRes val badgeRes: Int
) {
    Templates(Route.Templates, R.string.nav_templates, R.string.nav_badge_templates),
    Projects(Route.Projects, R.string.nav_projects, R.string.nav_badge_projects),
    Inbox(Route.Inbox, R.string.nav_inbox, R.string.nav_badge_inbox),
    Settings(Route.Settings, R.string.nav_settings, R.string.nav_badge_settings)
}

@Composable
fun rememberAppState(
    initialDestination: TopLevelDestination = TopLevelDestination.Templates
): AppState {
    val currentDestinationState = rememberSaveable { mutableStateOf(initialDestination) }
    
    val backStacks = TopLevelDestination.entries.associateWith { destination ->
        rememberNavBackStack(destination.screen)
    }
    
    val navigators = TopLevelDestination.entries.associateWith { destination ->
        val backStack = backStacks.getValue(destination)
        remember(backStack) { AppNavigatorImpl(backStack) }
    }
    
    return remember(backStacks, navigators) {
        AppState(
            currentDestinationState = currentDestinationState,
            backStacks = backStacks,
            navigators = navigators
        )
    }
}

class AppState(
    val currentDestinationState: MutableState<TopLevelDestination>,
    val backStacks: Map<TopLevelDestination, NavBackStack<NavKey>>,
    val navigators: Map<TopLevelDestination, AppNavigator>
) {
    var currentDestination: TopLevelDestination
        get() = currentDestinationState.value
        set(value) { currentDestinationState.value = value }

    val activeBackStack: NavBackStack<NavKey>
        get() = backStacks.getValue(currentDestination)
        
    val activeRootKey: NavKey
        get() = currentDestination.screen
        
    val activeNavigator: AppNavigator
        get() = navigators.getValue(currentDestination)

    val showBottomBar: Boolean get() = !activeNavigator.canPop
        
    fun handleBack() {
        if (!activeNavigator.popIfPossible()) {
            if (currentDestination != TopLevelDestination.Templates) {
                currentDestination = TopLevelDestination.Templates
            }
        }
    }

    fun selectDestination(destination: TopLevelDestination) {
        if (currentDestination == destination) activeNavigator.popToRoot()
        else currentDestination = destination
    }
}

@Composable
fun NavHost(
    modifier: Modifier = Modifier
) {
    val appState = rememberAppState()

    BackHandler(enabled = appState.activeNavigator.canPop || appState.currentDestination != TopLevelDestination.Templates) {
        appState.handleBack()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.colorBgElevated)
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            val backStack = appState.activeBackStack
            val navigator = appState.activeNavigator
            val fillModifier = Modifier.fillMaxSize()

            when (appState.currentDestination) {
                TopLevelDestination.Templates -> TemplateGraph(backStack, navigator, fillModifier)
                TopLevelDestination.Projects -> ProjectsGraph(backStack, navigator, fillModifier)
                TopLevelDestination.Inbox -> InboxGraph(backStack, navigator, fillModifier)
                TopLevelDestination.Settings -> SettingsGraph(backStack, navigator, fillModifier)
            }
        }

        if (appState.showBottomBar) {
            AppBottomBar(
                currentDestination = appState.currentDestination,
                onDestinationSelected = appState::selectDestination
            )
        }
    }
}

