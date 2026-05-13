# Navigation Architecture

This document outlines the navigation patterns and architecture used in the codebase.

## Overview

The app utilizes the experimental **Jetpack Navigation 3** (`androidx.navigation3`) library. This modern API completely abandons the string-based route approach in favor of a **strongly-typed, object-based** routing system. 

Key features of this setup include:
1. **Multiple Back Stacks**: The app maintains independent back stacks for each top-level tab, allowing users to switch tabs without losing their place.
2. **Type-Safe Arguments**: Screen arguments are passed natively through data classes.
3. **Decoupled Navigation**: Feature modules provide their own UI graphs and handle internal logic, while core modules provide the abstractions.

## Core Components

### 1. `Route` (`core:navigation`)
All screens in the application are defined in a single sealed interface `Route`, which extends `NavKey` and `Parcelable`.

- **No Arguments**: Defined as `data object`.
- **With Arguments**: Defined as `data class`.

```kotlin
sealed interface Route : NavKey, Parcelable {
    @Serializable
    @Parcelize
    data object Templates : Route

    @Serializable
    @Parcelize
    data class TemplateDetail(val templateId: String) : Route
}
```

### 2. `AppNavigator` (`core:navigation`)
A lightweight wrapper around the active `NavBackStack`. It exposes the essential navigation functions to feature graphs and screens.

- `navigate(route: Route)`: Adds a new screen to the back stack.
- `popIfPossible()`: Removes the current screen if the stack has more than one item.
- `popToRoot()`: Clears the stack back to the top-level destination.
- `canPop`: Boolean property useful for showing/hiding back buttons or the bottom navigation bar.

### 3. `AppState` and Multiple Back Stacks (`app` module)
In `NavHost.kt`, `rememberAppState()` generates the state for the root application structure. For every `TopLevelDestination` (e.g., Templates, Inbox), it creates:
- A dedicated `NavBackStack`
- A dedicated `AppNavigatorImpl` wrapping that stack

When the user selects a different bottom tab, `AppState` swaps out the `activeBackStack` and `activeNavigator`, preserving the state of the non-active tabs natively.

## Feature Implementation Guide

To add a new screen or flow to the application, follow these steps:

### Step 1: Define the Route
Add your new destination to `com.genesys.core.navigation.Route`.
```kotlin
@Serializable
@Parcelize
data class MyNewScreen(val myArgument: Int) : Route
```

### Step 2: Create the Feature Graph
Each feature module defines a main composable that orchestrates its screens. It receives the `NavBackStack` and `AppNavigator` from the `NavHost`.

Use `entryProvider` to map the `Route` classes to your specific Composables, and then pass this to `NavDisplay` to render the active screen.

```kotlin
@Composable
fun MyFeatureGraph(
    backStack: NavBackStack<NavKey>,
    navigator: AppNavigator,
    modifier: Modifier = Modifier
) {
    // Optional: Collect navigation side-effects from a ViewModel
    // viewModel.collectSideEffect { effect -> 
    //     when(effect) { is OpenDetail -> navigator.navigate(Route.MyNewScreen(effect.id)) }
    // }

    val entries = entryProvider<NavKey> {
        entry<Route.MyRootScreen> {
            MyRootComposable(
                onNavigate = { id -> navigator.navigate(Route.MyNewScreen(id)) }
            )
        }

        entry<Route.MyNewScreen> { destination ->
            // The destination object contains your type-safe arguments natively
            MyDetailComposable(
                id = destination.myArgument,
                onBack = navigator::popIfPossible
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
```

## UI Behaviors

- **Bottom Bar Visibility**: The `AppBottomBar` is dynamically shown or hidden based on whether the `activeNavigator.canPop` is true. This means the bottom bar is only visible on the root screen of any tab, and hides when you navigate deeper into a stack.
- **Back Handler**: The root `NavHost` automatically handles system back button presses, first attempting to pop the active stack (`appState.handleBack()`), and if that fails (meaning the user is at a root tab), it navigates back to the primary starting tab (`Templates`) before exiting the app.

## Cross-Feature Navigation

In a modularized architecture, feature modules (e.g., `feature-template`, `feature-projects`) should not depend on each other directly to prevent tight coupling and circular dependencies.

If a feature graph needs to navigate to or display a screen from another feature module, the orchestration must happen in the `app` module (where `NavHost` lives, as it depends on all features).

### Injecting Screens via Composable Lambda (Recommended)

If a graph needs to display a specific screen from another module without switching tabs, inject that screen as a `@Composable` parameter. This allows the graph to render the route inside its own `entryProvider` without knowing about the other module.

**1. Update the Target Graph:**
Update your feature graph to accept the external screen as a lambda parameter.

```kotlin
@Composable
fun TemplateGraph(
    backStack: NavBackStack<NavKey>,
    navigator: AppNavigator,
    // Inject the external screen here!
    projectSelectorScreen: @Composable (projectId: String, onBack: () -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    val entries = entryProvider<NavKey> {
        // ... existing local entries ...
        
        // Map the external route to the injected lambda
        entry<Route.ProjectSelector> { destination ->
            projectSelectorScreen(
                projectId = destination.projectId, 
                onBack = navigator::popIfPossible
            )
        }
    }
    // ...
}
```

**2. Provide the Screen in `NavHost.kt`:**
Because `NavHost.kt` resides in the `app` module, it has access to all feature module screens and can wire them together.

```kotlin
TopLevelDestination.Templates -> TemplateGraph(
    backStack = backStack,
    navigator = navigator,
    projectSelectorScreen = { projectId, onBack ->
        // Directly call the composable from the other feature module
        com.genesys.feature.projects.ui.ProjectSelectorScreen(
            projectId = projectId,
            onBack = onBack
        )
    },
    modifier = fillModifier
)
```

Alternatively, if you need to perform top-level navigation (i.e. switching bottom tabs), pass a standard callback like `onNavigateToProjects: () -> Unit` to your graph, and call `appState.selectDestination(TopLevelDestination.Projects)` from within `NavHost.kt`.
