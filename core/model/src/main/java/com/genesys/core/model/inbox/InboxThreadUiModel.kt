package com.genesys.core.model.inbox

data class InboxThreadUiModel(
    val id: String,
    val sender: String,
    val subject: String,
    val preview: String,
    val time: String,
    val category: String,
    val unread: Boolean,
    val filters: Set<InboxFilter>
)
