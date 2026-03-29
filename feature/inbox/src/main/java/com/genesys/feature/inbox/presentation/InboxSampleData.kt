package com.genesys.feature.inbox.presentation

import com.genesys.core.model.inbox.InboxFilter
import com.genesys.core.model.inbox.InboxGroupUiModel
import com.genesys.core.model.inbox.InboxThreadUiModel

internal val sampleInboxGroups = listOf(
    InboxGroupUiModel(
        id = "today",
        title = "Today",
        subtitle = "Priority queue",
        items = listOf(
            InboxThreadUiModel(
                id = "design-ops-review",
                sender = "Design Ops",
                subject = "Homepage motion review",
                preview = "Please approve the updated timing pass before the 4 PM handoff to engineering.",
                time = "09:24",
                category = "Approval",
                unread = true,
                filters = setOf(InboxFilter.Urgent, InboxFilter.Approvals)
            ),
            InboxThreadUiModel(
                id = "client-team-revision",
                sender = "Client Team",
                subject = "Revision notes for launch deck",
                preview = "Round-two feedback is in. The pricing slide needs a lighter narrative and a tighter CTA.",
                time = "10:12",
                category = "Feedback",
                unread = true,
                filters = setOf(InboxFilter.Urgent, InboxFilter.Mentions)
            )
        )
    ),
    InboxGroupUiModel(
        id = "earlier",
        title = "Earlier",
        subtitle = "Follow-ups",
        items = listOf(
            InboxThreadUiModel(
                id = "marketing-retro",
                sender = "Marketing",
                subject = "Campaign retro summary",
                preview = "The retro notes are complete and the next sprint suggestions are ready for prioritization.",
                time = "Yesterday",
                category = "Recap",
                unread = false,
                filters = setOf(InboxFilter.Mentions)
            ),
            InboxThreadUiModel(
                id = "automation-export",
                sender = "Automation",
                subject = "Asset export completed",
                preview = "All queued exports finished successfully and were published to the shared delivery folder.",
                time = "Yesterday",
                category = "System",
                unread = false,
                filters = setOf(InboxFilter.Approvals)
            )
        )
    )
)
