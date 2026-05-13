package com.genesys.feature.template.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.genesys.core.designsystem.component.AppSectionHeader
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.template.Template
import com.genesys.core.model.template.TemplateCollections
import com.genesys.feature.template.R

@Composable
fun CollectionSection(
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
