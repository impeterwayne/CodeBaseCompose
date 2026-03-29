package com.genesys.core.model.settings

data class SettingGroup(
    val title: String,
    val subtitle: String,
    val items: List<SettingItem>
)
