package com.genesys.core.model.inbox

data class InboxGroupUiModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val items: List<InboxThreadUiModel>
)
