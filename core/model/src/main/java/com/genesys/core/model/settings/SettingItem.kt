package com.genesys.core.model.settings

data class SettingItem(
    val title: String,
    val description: String,
    val value: String,
    val highlighted: Boolean
)
