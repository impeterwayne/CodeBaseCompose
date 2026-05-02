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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.GenesysChip
import com.genesys.core.designsystem.component.GenesysDivider
import com.genesys.core.designsystem.component.GenesysPageFrame
import com.genesys.core.designsystem.component.GenesysPanel
import com.genesys.core.designsystem.component.GenesysPanelTone
import com.genesys.core.designsystem.component.GenesysSectionHeader
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.component.ErrorState
import com.genesys.core.designsystem.component.LoadingIndicator
import com.genesys.core.designsystem.theme.GenesysTheme
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
    GenesysPageFrame(
        modifier = modifier,
        contentPadding = PaddingValues(0.dp)
    ) {
        when {
            state.isLoading -> {
                LoadingIndicator(
                    modifier = Modifier.fillMaxSize()
                )
            }

            state.colorErrorMessage != null -> {
                ErrorState(
                    message = state.colorErrorMessage ?: "Something went wrong",
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
        contentPadding = PaddingValues(vertical = GenesysTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xxl)
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
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
    ) {
        GenesysSectionHeader(
            title = collection.name,
            subtitle = "${collection.templates.size} templates",
            modifier = Modifier.padding(horizontal = GenesysTheme.spacing.md)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = GenesysTheme.spacing.md),
            horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
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
    GenesysPanel(
        modifier = modifier.width(TemplateCardWidth),
        tone = GenesysPanelTone.Raised,
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TemplateHero(template = template)
            GenesysDivider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(GenesysTheme.spacing.md),
                verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
            ) {
                GenesysText(
                    text = template.name,
                    style = GenesysTheme.typography.titleLarge,
                    color = GenesysTheme.colorScheme.colorText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                GenesysText(
                    text = if (template.premium) "Premium template" else "Template",
                    style = GenesysTheme.typography.labelMedium,
                    color = GenesysTheme.colorScheme.colorBorder
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
        GenesysTheme.colorScheme.colorPrimary
    } else {
        GenesysTheme.colorScheme.colorBgElevated
    }
    val heroContent = if (template.premium) {
        GenesysTheme.colorScheme.colorTextOnPrimary
    } else {
        GenesysTheme.colorScheme.colorPrimary
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(TemplateHeroHeight)
            .background(heroBackground)
            .padding(GenesysTheme.spacing.md)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
        ) {
            if (template.premium) {
                GenesysChip(
                    text = "Premium",
                    selected = true
                )
            }
            GenesysText(
                text = template.ratio,
                style = GenesysTheme.typography.headlineMedium,
                color = heroContent
            )
        }

        if (!template.premium) {
            GenesysChip(
                text = "Standard",
                selected = false,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}
