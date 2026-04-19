package com.genesys.core.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

interface GenesysNavigator {
    fun navigate(screen: GenesysScreen)

    fun popBackStack()
}

class GenesysNavigatorImpl(
    private val backStack: NavBackStack<NavKey>
) : GenesysNavigator {
    override fun navigate(screen: GenesysScreen) {
        if (backStack.lastOrNull() != screen) {
            backStack.add(screen)
        }
    }

    override fun popBackStack() {
        backStack.removeLastOrNull()
    }
}
