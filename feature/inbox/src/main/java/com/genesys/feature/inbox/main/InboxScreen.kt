package com.genesys.feature.inbox.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.GenesysChip
import com.genesys.core.designsystem.component.GenesysPageFrame
import com.genesys.core.designsystem.component.GenesysPanel
import com.genesys.core.designsystem.component.GenesysPanelTone
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysSectionHeader
import com.genesys.core.designsystem.component.GenesysSecondaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme

private data class InboxThread(
    val sender: String,
    val subject: String,
    val preview: String,
    val time: String,
    val category: String,
    val unread: Boolean
)

private data class InboxGroup(
    val title: String,
    val subtitle: String,
    val items: List<InboxThread>
)

private val inboxGroups = listOf(
    InboxGroup(
        title = "Today",
        subtitle = "Priority queue",
        items = listOf(
            InboxThread(
                sender = "Design Ops",
                subject = "Homepage motion review",
                preview = "Please approve the updated timing pass before the 4 PM handoff to engineering.",
                time = "09:24",
                category = "Approval",
                unread = true
            ),
            InboxThread(
                sender = "Client Team",
                subject = "Revision notes for launch deck",
                preview = "Round-two feedback is in. The pricing slide needs a lighter narrative and a tighter CTA.",
                time = "10:12",
                category = "Feedback",
                unread = true
            )
        )
    ),
    InboxGroup(
        title = "Earlier",
        subtitle = "Follow-ups",
        items = listOf(
            InboxThread(
                sender = "Marketing",
                subject = "Campaign retro summary",
                preview = "The retro notes are complete and the next sprint suggestions are ready for prioritization.",
                time = "Yesterday",
                category = "Recap",
                unread = false
            ),
            InboxThread(
                sender = "Automation",
                subject = "Asset export completed",
                preview = "All queued exports finished successfully and were published to the shared delivery folder.",
                time = "Yesterday",
                category = "System",
                unread = false
            )
        )
    )
)

@Composable
fun InboxScreen(
    modifier: Modifier = Modifier
) {
    GenesysPageFrame(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(0.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(GenesysTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.lg)
        ) {
            item {
                InboxHero()
            }

            inboxGroups.forEach { group ->
                item {
                    GenesysSectionHeader(
                        title = group.title,
                        subtitle = group.subtitle
                    )
                }

                items(
                    items = group.items,
                    key = { "${group.title}-${it.subject}" }
                ) { thread ->
                    InboxThreadCard(thread = thread)
                }
            }
        }
    }
}

@Composable
private fun InboxHero() {
    GenesysPanel(
        tone = GenesysPanelTone.Heavy
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
            ) {
                GenesysText(
                    text = "Inbox",
                    style = GenesysTheme.typography.headlineSmall,
                    color = GenesysTheme.colors.onPrimaryContainer
                )
                GenesysText(
                    text = "Two approvals and one client response should be handled before end of day.",
                    style = GenesysTheme.typography.bodyLarge,
                    color = GenesysTheme.colors.onPrimaryContainer
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
            ) {
                GenesysChip(text = "Urgent", selected = true)
                GenesysChip(text = "Approvals", selected = false)
                GenesysChip(text = "Mentions", selected = false)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
            ) {
                GenesysPrimaryButton(
                    text = "Open queue",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
                GenesysSecondaryButton(
                    text = "Mark all read",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun InboxThreadCard(
    thread: InboxThread
) {
    GenesysPanel(
        tone = GenesysPanelTone.Raised
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GenesysText(
                    text = thread.sender,
                    style = GenesysTheme.typography.labelLarge
                )
                GenesysText(
                    text = thread.time,
                    style = GenesysTheme.typography.labelMedium,
                    color = GenesysTheme.colors.outline
                )
            }

            GenesysText(
                text = thread.subject,
                style = GenesysTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            GenesysText(
                text = thread.preview,
                style = GenesysTheme.typography.bodyLarge
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
            ) {
                GenesysChip(
                    text = thread.category,
                    selected = thread.unread
                )
                GenesysChip(
                    text = if (thread.unread) "Unread" else "Read",
                    selected = thread.unread
                )
            }
        }
    }
}
