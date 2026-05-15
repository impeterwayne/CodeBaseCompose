package com.genesys.feature.template.main.components

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.template.Template
import com.genesys.core.model.template.TemplateCollections

@Composable
fun TemplateCollectionsList(
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

@Preview
@Composable
private fun TemplateCollectionsListPreview() {
    AppTheme {
        TemplateCollectionsList(
            collections = listOf(com.genesys.core.model.template.TemplateCollections(name = "Featured", templates = listOf())),
            onTemplateClick = {}
        )
    }
}
