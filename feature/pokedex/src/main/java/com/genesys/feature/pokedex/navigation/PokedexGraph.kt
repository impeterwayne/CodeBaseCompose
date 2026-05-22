package com.genesys.feature.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.genesys.core.navigation.AppNavigator
import com.genesys.core.navigation.Route
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.genesys.feature.pokedex.presentation.detail.PokedexDetailRoute
import com.genesys.feature.pokedex.presentation.list.PokedexRoute

@Composable
fun PokedexGraph(
    backStack: NavBackStack<NavKey>,
    navigator: AppNavigator,
    modifier: Modifier = Modifier
) {
    val entries = entryProvider<NavKey> {
        entry<Route.Pokedex> {
            PokedexRoute(
                navigator = navigator,
                modifier = modifier
            )
        }

        entry<Route.PokedexDetail> {
            PokedexDetailRoute(
                onBack = navigator::popIfPossible,
                modifier = modifier
            )
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = navigator::popIfPossible,
        entryProvider = entries,
        modifier = modifier
    )
}
