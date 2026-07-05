package com.genesys.codebase.navigation

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
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
import com.genesys.feature.feature2.navigation.Feature2Graph
import com.genesys.feature.feature1.navigation.Feature1Graph
import com.genesys.feature.feature3.navigation.Feature3Graph
import com.genesys.feature.pokedex.navigation.PokedexGraph

enum class TopLevelDestination(
    val screen: Route,
    @StringRes val labelRes: Int,
    @DrawableRes val iconRes: Int
) {
    Pokedex(Route.Pokedex, R.string.nav_pokedex, R.drawable.ic_nav_templates),
    Feature1(Route.Feature1, R.string.nav_feature1, R.drawable.ic_nav_projects),
    Feature2(Route.Feature2, R.string.nav_feature2, R.drawable.ic_nav_inbox),
    Feature3(Route.Feature3, R.string.nav_feature3, R.drawable.ic_nav_settings)
}

@Composable
fun rememberAppState(
    initialDestination: TopLevelDestination = TopLevelDestination.Pokedex
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
            if (currentDestination != TopLevelDestination.Pokedex) {
                currentDestination = TopLevelDestination.Pokedex
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

    BackHandler(enabled = appState.activeNavigator.canPop || appState.currentDestination != TopLevelDestination.Pokedex) {
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
                TopLevelDestination.Pokedex -> PokedexGraph(backStack, navigator, fillModifier)
                TopLevelDestination.Feature1 -> Feature1Graph(backStack, navigator, fillModifier)
                TopLevelDestination.Feature2 -> Feature2Graph(backStack, navigator, fillModifier)
                TopLevelDestination.Feature3 -> Feature3Graph(backStack, navigator, fillModifier)
                else -> {}
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

