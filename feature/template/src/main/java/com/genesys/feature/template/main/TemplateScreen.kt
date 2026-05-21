package com.genesys.feature.template.main

import androidx.compose.ui.tooling.preview.Preview
import com.genesys.core.designsystem.theme.AppTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.AppPageFrame
import com.genesys.core.designsystem.component.ErrorState
import com.genesys.core.designsystem.component.LoadingIndicator
import com.genesys.core.model.template.Template
import com.genesys.feature.template.R
import com.genesys.feature.template.main.components.TemplateCollectionsList

@Composable
fun TemplateScreen(
    state: MainUiState,
    onRetry: () -> Unit,
    onTemplateClick: (Template) -> Unit,
    modifier: Modifier = Modifier
) {
    AppPageFrame(
        modifier = modifier,
        contentPadding = PaddingValues(0.dp)
    ) {
        when {
            state.isLoading -> {
                LoadingIndicator(
                    modifier = Modifier.fillMaxSize()
                )
            }

            state.errorMessage != null -> {
                ErrorState(
                    message = state.errorMessage ?: stringResource(R.string.template_error_generic),
                    onRetry = onRetry,
                    modifier = Modifier.fillMaxSize()
                )
            }

            else -> {
                TemplateCollectionsList(
                    collections = state.templateCollections,
                    onTemplateClick = onTemplateClick
                )
            }
        }
    }
}

@Composable
fun TemplateScreenContent(
    state: MainUiState,
    onRetry: () -> Unit,
    onTemplateClick: (Template) -> Unit
) {
    TemplateScreen(
        state = state,
        onRetry = onRetry,
        onTemplateClick = onTemplateClick
    )
}

@Preview
@Composable
private fun TemplateScreenPreview() {
    AppTheme {
        TemplateScreen(state = MainUiState(), onRetry = {}, onTemplateClick = {})
    }
}
