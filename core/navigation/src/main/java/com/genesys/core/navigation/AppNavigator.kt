package com.genesys.core.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

interface AppNavigator {
    val canPop: Boolean

    fun navigate(route: Route)

    fun popIfPossible(): Boolean

    fun popToRoot()
}

class AppNavigatorImpl(
    private val backStack: NavBackStack<NavKey>
) : AppNavigator {
    override val canPop: Boolean
        get() = backStack.size > 1

    override fun navigate(route: Route) {
        if (backStack.lastOrNull() != route) {
            backStack.add(route)
        }
    }

    override fun popIfPossible(): Boolean {
        if (!canPop) return false

        backStack.removeLastOrNull()
        return true
    }

    override fun popToRoot() {
        while (canPop) {
            backStack.removeLastOrNull()
        }
    }
}
