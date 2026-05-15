package com.genesys.core.designsystem.component

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.genesys.core.designsystem.theme.AppTheme
import java.util.Locale

@Composable
fun AppSectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.xs)
    ) {
        subtitle?.let {
            AppText(
                text = it.uppercase(Locale.ROOT),
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colorScheme.colorBorder
            )
        }
        AppText(
            text = title,
            style = AppTheme.typography.headlineSmall,
            color = AppTheme.colorScheme.colorText
        )
        AppDivider()
    }
}

@Preview
@Composable
private fun AppSectionHeaderPreview() {
    AppTheme {
        AppSectionHeader(title = "Section Title", subtitle = "Section Subtitle")
    }
}
