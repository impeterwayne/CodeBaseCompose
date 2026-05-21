package com.genesys.feature.template.main.components

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.AppChip
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.template.Template
import com.genesys.feature.template.R

val TemplateHeroHeight = 88.dp

@Composable
fun TemplateHero(
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

@Preview
@Composable
private fun TemplateHeroPreview() {
    AppTheme {
        TemplateHero(
            template = Template(name = "Hero Template")
        )
    }
}
