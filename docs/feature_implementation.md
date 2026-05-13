# Feature Implementation Guidelines

This document outlines the standard architecture pattern used for creating a new Feature in this project. All features should follow a clean architecture approach, specifically leveraging the **Orbit MVI** Unidirectional Data Flow (UDF) pattern, to ensure consistency, testability, and separation of concerns.

## Overview

The feature implementation in this project is divided into five main parts:
1. **Contract (`[Feature]Contract.kt`)**: Defines the `UiState`, `Action`, and `SideEffect` for the feature.
2. **ViewModel (`[Feature]ViewModel.kt`)**: Implements `BaseViewModel` to manage state mutations and side effects via UseCases.
3. **Screen (`[Feature]Screen.kt`)**: A stateless Jetpack Compose function that orchestrates the screen layout based solely on `UiState` and emits `Action`s back to the caller.
4. **Components (`components/` package)**: Individual, focused UI composables (like cards, heroes, or lists) extracted into their own files to keep the main Screen file readable.
5. **Graph (`[Feature]Graph.kt`)**: The navigation entry point that hoists the `ViewModel`, collects the state, and wires up the `Screen`.

---

## 1. The Contract

The Contract defines the strict boundaries of communication between the UI and the ViewModel.

Create a `[Feature]Contract.kt` file containing:

```kotlin
import com.genesys.core.common.base.UiState
import com.genesys.core.common.base.Action
import com.genesys.core.common.base.SideEffect

// 1. UiState: Represents the entire state of the screen at a given moment.
data class FeatureUiState(
    val isLoading: Boolean = false,
    val data: List<MyModel> = emptyList()
) : UiState

// 2. Action: Represents user intents or lifecycle events from the UI.
sealed interface FeatureAction : Action {
    data class ItemClicked(val id: String) : FeatureAction
    data object RefreshRequested : FeatureAction
}

// 3. SideEffect: One-off events (like navigation, showing a toast) that shouldn't be persisted in state.
sealed interface FeatureSideEffect : SideEffect {
    data class NavigateToDetail(val id: String) : FeatureSideEffect
    data class ShowSnackbar(val message: String) : FeatureSideEffect
}
```

---

## 2. The ViewModel

The ViewModel handles the business logic. It takes in UseCases from the `core:domain` module and produces new states using the `intent` block.

Create a `[Feature]ViewModel.kt`:

```kotlin
import com.genesys.core.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FeatureViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase
) : BaseViewModel<FeatureUiState, FeatureSideEffect, FeatureAction>() {

    // Define the initial state of the container
    override val container = container<FeatureUiState, FeatureSideEffect>(
        FeatureUiState(
            data = getDataUseCase()
        )
    )

    // Handle incoming actions from the UI
    override fun onAction(action: FeatureAction) {
        when (action) {
            is FeatureAction.ItemClicked -> handleItemClicked(action.id)
            FeatureAction.RefreshRequested -> refreshData()
        }
    }

    private fun handleItemClicked(id: String) = intent {
        // Example of a side effect (e.g., navigation)
        postSideEffect(FeatureSideEffect.NavigateToDetail(id))
    }

    private fun refreshData() = intent {
        // 1. Mutate state to show loading
        reduce { state.copy(isLoading = true) }
        
        // 2. Perform work (e.g., fetch data via UseCase)
        val newData = getDataUseCase()
        
        // 3. Mutate state with new data
        reduce { state.copy(isLoading = false, data = newData) }
    }
}
```

---

## 3. The Stateless Screen & Components

The screen should be completely decoupled from the ViewModel and Navigation. It only depends on `UiState` and a lambda for emitting `Action`s. To improve readability, the `[Feature]Screen.kt` file should primarily orchestrate the layout and delegate to specific UI components.

Individual composable components (such as cards, lists, or hero sections) must be extracted into their own files within a `components` subpackage.

Create a `[Feature]Screen.kt`:

```kotlin
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.genesys.feature.myfeature.presentation.components.MyList
import com.genesys.core.designsystem.component.LoadingIndicator

@Composable
fun FeatureScreen(
    state: FeatureUiState,
    onAction: (FeatureAction) -> Unit,
    modifier: Modifier = Modifier
) {
    // Render UI based purely on `state`
    if (state.isLoading) {
        LoadingIndicator()
    } else {
        MyList(
            items = state.data,
            onItemClick = { id -> onAction(FeatureAction.ItemClicked(id)) }
        )
    }
}
```

---

## 4. The Graph (Wiring it together)

The Graph acts as the glue layer. It instantiates the ViewModel (via Hilt), collects its state, and passes both state and action handlers down to the Screen.

Create a `[Feature]Graph.kt`:

```kotlin
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.genesys.core.navigation.AppNavigator
import com.genesys.core.navigation.Route
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun FeatureGraph(
    backStack: NavBackStack<NavKey>,
    navigator: AppNavigator,
    modifier: Modifier = Modifier,
    viewModel: FeatureViewModel = hiltViewModel()
) {
    // 1. Collect State
    val state by viewModel.collectAsState()

    // 2. Handle Side Effects (Optional, for navigation/toasts)
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is FeatureSideEffect.NavigateToDetail -> {
                // e.g., navigator.push(Route.Detail(sideEffect.id))
            }
            is FeatureSideEffect.ShowSnackbar -> {
                // e.g., show snackbar
            }
        }
    }

    // 3. Provide Entry
    val entries = entryProvider<NavKey> {
        entry<Route.Feature> {
            FeatureScreen(
                state = state,
                onAction = viewModel::onAction, // Pass the ViewModel's action handler
                modifier = modifier
            )
        }
    }

    // 4. Display Navigation
    NavDisplay(
        backStack = backStack,
        onBack = navigator::popIfPossible,
        entryProvider = entries,
        modifier = modifier
    )
}
```

## Summary of Rules

1. **Never pass a `ViewModel` directly into a `Screen` composable.** Always hoist it at the `Graph` or route level and pass down the `UiState` and `onAction` lambda.
2. **All state mutations must happen within `intent { reduce { ... } }`** inside the ViewModel.
3. **Data must be fetched via UseCases from `core:domain`**, never hardcoded in the Presentation layer or requested directly from `core:data`.
4. **Ensure `orbitCompose` and `orbitViewmodel` are included in the feature module's `build.gradle.kts`.**
5. **Extract UI components into a `components` package.** The main `Screen` file should act as an orchestrator. Individual UI pieces should live in separate files in a `components` subpackage to keep files small and readable.
