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

private val TemplateCardWidth = 196.dp
private val TemplateHeroHeight = 88.dp

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

@Composable
private fun TemplateCollectionsList(
    collections: List<TemplateCollections>,
    onTemplateClick: (Template) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = AppTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.xxl)
    ) {
        items(
            items = collections,
            key = { it.id }
        ) { collection ->
            CollectionSection(
                collection = collection,
                onTemplateClick = onTemplateClick
            )
        }
    }
}

@Composable
private fun CollectionSection(
    collection: TemplateCollections,
    onTemplateClick: (Template) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.md)
    ) {
        AppSectionHeader(
            title = collection.name,
            subtitle = stringResource(R.string.template_count, collection.templates.size),
            modifier = Modifier.padding(horizontal = AppTheme.spacing.md)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = AppTheme.spacing.md),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
        ) {
            items(
                items = collection.templates,
                key = { it.id }
            ) { template ->
                TemplateItem(
                    template = template,
                    onClick = { onTemplateClick(template) }
                )
            }
        }
    }
}

@Composable
private fun TemplateItem(
    template: Template,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppPanel(
        modifier = modifier.width(TemplateCardWidth),
        tone = AppPanelTone.Raised,
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
        onClickLabel = stringResource(R.string.template_open_label),
        role = Role.Button
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TemplateHero(template = template)
            AppDivider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.spacing.md),
                verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.xs)
            ) {
                AppText(
                    text = template.name,
                    style = AppTheme.typography.titleLarge,
                    color = AppTheme.colorScheme.colorText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                AppText(
                    text = if (template.premium) {
                        stringResource(R.string.template_premium_label)
                    } else {
                        stringResource(R.string.template_standard_label)
                    },
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colorScheme.colorBorder
                )
            }
        }
    }
}

@Composable
private fun TemplateHero(
    template: Template,
    modifier: Modifier = Modifier
) {
    val heroBackground = if (template.premium) {
        AppTheme.colorScheme.colorPrimary
    } else {
        AppTheme.colorScheme.colorBgElevated
    }
    val heroContent = if (template.premium) {
        AppTheme.colorScheme.colorTextOnPrimary
    } else {
        AppTheme.colorScheme.colorPrimary
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(TemplateHeroHeight)
            .background(heroBackground)
            .padding(AppTheme.spacing.md)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
        ) {
            AppText(
                text = template.ratio,
                style = AppTheme.typography.headlineMedium,
                color = heroContent
            )
        }

        if (template.premium) {
            AppChip(
                text = stringResource(R.string.template_premium_chip),
                selected = true,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        } else {
            AppChip(
                text = stringResource(R.string.template_standard_chip),
                selected = false,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}
