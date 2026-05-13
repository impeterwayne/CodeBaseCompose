package com.genesys.core.domain.usecase.settings

import com.genesys.core.model.settings.SettingGroup
import com.genesys.core.model.settings.SettingItem
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor() {
    operator fun invoke(): List<SettingGroup> {
        return listOf(
            SettingGroup(
                title = "Workspace",
                subtitle = "Operations",
                items = listOf(
                    SettingItem(
                        title = "Default project view",
                        description = "Choose the landing view shown when opening a project workspace.",
                        value = "Board",
                        highlighted = true
                    ),
                    SettingItem(
                        title = "Shared review mode",
                        description = "Keep external review links enabled for current collaborators.",
                        value = "Enabled",
                        highlighted = true
                    )
                )
            ),
            SettingGroup(
                title = "Notifications",
                subtitle = "Signal control",
                items = listOf(
                    SettingItem(
                        title = "Approval reminders",
                        description = "Receive reminders when pending approvals are close to their due time.",
                        value = "Every 2 hours",
                        highlighted = false
                    ),
                    SettingItem(
                        title = "Digest delivery",
                        description = "Bundle low-priority updates into a single summary instead of individual pings.",
                        value = "08:30 daily",
                        highlighted = false
                    )
                )
            ),
            SettingGroup(
                title = "Security",
                subtitle = "Access",
                items = listOf(
                    SettingItem(
                        title = "Session verification",
                        description = "Require a fresh verification step before downloading client delivery assets.",
                        value = "Required",
                        highlighted = true
                    ),
                    SettingItem(
                        title = "Device trust window",
                        description = "How long a signed-in device stays trusted before a new verification challenge.",
                        value = "14 days",
                        highlighted = false
                    )
                )
            )
        )
    }
}
