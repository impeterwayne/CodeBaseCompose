package com.genesys.feature.template.main.components

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.AppDivider
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.template.Template
import com.genesys.feature.template.R

val TemplateCardWidth = 196.dp

@Composable
fun TemplateItem(
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

@Preview
@Composable
private fun TemplateItemPreview() {
    AppTheme {
        TemplateItem(
            template = com.genesys.core.model.template.Template(name = "Item Template"),
            onClick = {}
        )
    }
}
