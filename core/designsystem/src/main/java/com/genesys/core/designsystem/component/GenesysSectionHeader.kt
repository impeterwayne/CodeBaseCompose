package com.genesys.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.genesys.core.designsystem.theme.GenesysTheme
import java.util.Locale

@Composable
fun GenesysSectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
    ) {
        subtitle?.let {
            GenesysText(
                text = it.uppercase(Locale.ROOT),
                style = GenesysTheme.typography.labelMedium,
                color = GenesysTheme.colorScheme.colorBorder
            )
        }
        GenesysText(
            text = title,
            style = GenesysTheme.typography.headlineSmall,
            color = GenesysTheme.colorScheme.colorText
        )
        GenesysDivider()
    }
}
