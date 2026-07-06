package com.genesys.feature.feature1.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.AddActionButton
import com.genesys.core.designsystem.component.AppDivider
import com.genesys.core.designsystem.component.AppGradientTransition
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppPrimaryButton
import com.genesys.core.designsystem.component.AppSecondaryButton
import com.genesys.core.designsystem.component.AppWarningButton
import com.genesys.core.designsystem.component.AppChip
import com.genesys.core.designsystem.component.AppSectionHeader
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.component.ErrorState
import com.genesys.core.designsystem.component.GradientDirection
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.feature.feature1.R

@Composable
fun Feature1Screen(
    modifier: Modifier = Modifier
) {
    var isChip1Selected by remember { mutableStateOf(true) }
    var isChip2Selected by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.colorBgLayout)
            .statusBarsPadding(),
        contentPadding = PaddingValues(
            start = AppTheme.spacing.md,
            top = AppTheme.spacing.md,
            end = AppTheme.spacing.md,
            bottom = AppTheme.spacing.xxl
        ),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.lg)
    ) {
        // Title & Header
        item {
            AppSectionHeader(
                title = stringResource(R.string.component_showcase_title),
                subtitle = stringResource(R.string.component_showcase_subtitle)
            )
        }

        // Typography
        item {
            Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)) {
                AppSectionHeader(
                    title = stringResource(R.string.component_showcase_typography_title)
                )
                
                AppText("Display Large", style = AppTheme.typography.displayLarge)
                AppText("Display Medium", style = AppTheme.typography.displayMedium)
                AppText("Display Small", style = AppTheme.typography.displaySmall)
                AppText("Headline Large", style = AppTheme.typography.headlineLarge)
                AppText("Headline Medium", style = AppTheme.typography.headlineMedium)
                AppText("Headline Small", style = AppTheme.typography.headlineSmall)
                AppText("Title Large", style = AppTheme.typography.titleLarge)
                AppText("Title Medium", style = AppTheme.typography.titleMedium)
                AppText("Title Small", style = AppTheme.typography.titleSmall)
                AppText("Body Large", style = AppTheme.typography.bodyLarge)
                AppText("Body Medium", style = AppTheme.typography.bodyMedium)
                AppText("Body Small", style = AppTheme.typography.bodySmall)
                AppText("Label Large", style = AppTheme.typography.labelLarge)
                AppText("Label Medium", style = AppTheme.typography.labelMedium)
                AppText("Label Small", style = AppTheme.typography.labelSmall)
            }
        }

        // Buttons
        item {
            Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)) {
                AppSectionHeader(
                    title = stringResource(R.string.component_showcase_buttons_title)
                )
                
                AppPrimaryButton(
                    text = stringResource(R.string.component_showcase_btn_primary),
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
                
                AppSecondaryButton(
                    text = stringResource(R.string.component_showcase_btn_secondary),
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
                
                AppWarningButton(
                    text = stringResource(R.string.component_showcase_btn_warning),
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )

                // AddActionButton Demo Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(
                            color = AppTheme.colorScheme.colorBgContainer,
                            shape = AppTheme.shapes.shape6
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    AddActionButton(
                        onClick = {},
                        contentDescription = "Showcase Add Button"
                    )
                }
            }
        }

        // Chips
        item {
            Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)) {
                AppSectionHeader(
                    title = stringResource(R.string.component_showcase_chips_title)
                )
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
                ) {
                    AppChip(
                        text = stringResource(R.string.component_showcase_chip_selected),
                        selected = isChip1Selected,
                        onClick = {
                            isChip1Selected = !isChip1Selected
                        }
                    )
                    
                    AppChip(
                        text = stringResource(R.string.component_showcase_chip_unselected),
                        selected = isChip2Selected,
                        onClick = {
                            isChip2Selected = !isChip2Selected
                        }
                    )
                }
            }
        }

        // Panels
        item {
            Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)) {
                AppSectionHeader(
                    title = stringResource(R.string.component_showcase_panels_title)
                )
                
                Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
                    ) {
                        AppPanel(
                            tone = AppPanelTone.Frame,
                            modifier = Modifier.weight(1f)
                        ) {
                            AppText("Frame Tone", style = AppTheme.typography.titleMedium)
                        }
                        
                        AppPanel(
                            tone = AppPanelTone.Raised,
                            modifier = Modifier.weight(1f)
                        ) {
                            AppText("Raised Tone", style = AppTheme.typography.titleMedium)
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
                    ) {
                        AppPanel(
                            tone = AppPanelTone.Heavy,
                            modifier = Modifier.weight(1f)
                        ) {
                            AppText("Heavy Tone", style = AppTheme.typography.titleMedium, color = AppTheme.colorScheme.colorTextOnPrimary)
                        }

                        AppPanel(
                            tone = AppPanelTone.Error,
                            modifier = Modifier.weight(1f)
                        ) {
                            AppText("Error Tone", style = AppTheme.typography.titleMedium, color = AppTheme.colorScheme.colorError)
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
                    ) {
                        AppPanel(
                            tone = AppPanelTone.Warning,
                            modifier = Modifier.weight(1f)
                        ) {
                            AppText("Warning Tone", style = AppTheme.typography.titleMedium, color = AppTheme.colorScheme.colorWarning)
                        }

                        AppPanel(
                            tone = AppPanelTone.Success,
                            modifier = Modifier.weight(1f)
                        ) {
                            AppText("Success Tone", style = AppTheme.typography.titleMedium, color = AppTheme.colorScheme.colorSuccess)
                        }
                    }
                }
            }
        }

        // Dividers
        item {
            Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)) {
                AppSectionHeader(
                    title = stringResource(R.string.component_showcase_dividers_title)
                )
                
                AppText("Default Divider:")
                AppDivider()
                
                AppText("Custom Divider (Thickness = 4.dp, Primary Color):")
                AppDivider(
                    thickness = 4.dp,
                    color = AppTheme.colorScheme.colorPrimary
                )
            }
        }

        // Error States
        item {
            Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)) {
                AppSectionHeader(
                    title = stringResource(R.string.component_showcase_error_states_title)
                )
                
                ErrorState(
                    message = stringResource(R.string.component_showcase_error_msg),
                    onRetry = {},
                    retryText = stringResource(R.string.component_showcase_error_retry)
                )
            }
        }
    }
}
