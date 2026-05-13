package com.genesys.feature.template.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.genesys.feature.template.R
import com.genesys.core.designsystem.component.AppChip
import com.genesys.core.designsystem.component.AppDivider
import com.genesys.core.designsystem.component.AppPageFrame
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppSectionHeader
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.component.ErrorState
import com.genesys.core.designsystem.component.LoadingIndicator
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.template.Template
import com.genesys.core.model.template.TemplateCollections

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
