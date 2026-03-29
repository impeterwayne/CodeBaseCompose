package com.genesys.core.model.inbox

enum class InboxFilter(
    val label: String
) {
    Urgent(label = "Urgent"),
    Approvals(label = "Approvals"),
    Mentions(label = "Mentions")
}
