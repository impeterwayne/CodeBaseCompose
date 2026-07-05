@file:OptIn(androidx.compose.foundation.style.ExperimentalFoundationStyleApi::class)

package com.genesys.codebase.navigation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.genesys.codebase.R
import com.genesys.core.designsystem.component.AddActionButton
import com.genesys.core.designsystem.component.AppBottomBar
import com.genesys.core.designsystem.component.AppBottomTabItem
import com.genesys.core.designsystem.component.AppGradientTransition
import com.genesys.core.designsystem.component.GradientDirection
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

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.colorBgLayout)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
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
                }

                if (appState.showBottomBar) {
                    AppGradientTransition(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        height = 48.dp,
                        direction = GradientDirection.BottomToTop
                    )
                }
            }

            if (appState.showBottomBar) {
                AppBottomBar {
                    val currentDestination = appState.currentDestination
                    val onDestinationSelected = appState::selectDestination
                    val neoOrange = AppTheme.colorScheme.neoSelectedOrange
                    val outlineColor = AppTheme.colorScheme.neoBorder

                    // Left Items
                    AppBottomTabItem(
                        painter = painterResource(id = TopLevelDestination.Pokedex.iconRes),
                        label = stringResource(id = TopLevelDestination.Pokedex.labelRes),
                        selected = currentDestination == TopLevelDestination.Pokedex,
                        onClick = { onDestinationSelected(TopLevelDestination.Pokedex) },
                        activeColor = neoOrange,
                        inactiveColor = outlineColor,
                        modifier = Modifier.weight(1f)
                    )

                    AppBottomTabItem(
                        painter = painterResource(id = TopLevelDestination.Feature1.iconRes),
                        label = stringResource(id = TopLevelDestination.Feature1.labelRes),
                        selected = currentDestination == TopLevelDestination.Feature1,
                        onClick = { onDestinationSelected(TopLevelDestination.Feature1) },
                        activeColor = neoOrange,
                        inactiveColor = outlineColor,
                        modifier = Modifier.weight(1f)
                    )

                    // Middle Placeholder Item (Spacer)
                    Spacer(modifier = Modifier.weight(1f))

                    // Right Items
                    AppBottomTabItem(
                        painter = painterResource(id = TopLevelDestination.Feature2.iconRes),
                        label = stringResource(id = TopLevelDestination.Feature2.labelRes),
                        selected = currentDestination == TopLevelDestination.Feature2,
                        onClick = { onDestinationSelected(TopLevelDestination.Feature2) },
                        activeColor = neoOrange,
                        inactiveColor = outlineColor,
                        modifier = Modifier.weight(1f)
                    )

                    AppBottomTabItem(
                        painter = painterResource(id = TopLevelDestination.Feature3.iconRes),
                        label = stringResource(id = TopLevelDestination.Feature3.labelRes),
                        selected = currentDestination == TopLevelDestination.Feature3,
                        onClick = { onDestinationSelected(TopLevelDestination.Feature3) },
                        activeColor = neoOrange,
                        inactiveColor = outlineColor,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        if (appState.showBottomBar) {
            val context = LocalContext.current
            AddActionButton(
                onClick = {

                },
                contentDescription = stringResource(id = R.string.nav_primary),
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

