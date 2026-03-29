package com.genesys.feature.inbox.presentation

import com.genesys.core.model.inbox.InboxFilter
import com.genesys.core.model.inbox.InboxGroupUiModel
import com.genesys.core.model.inbox.InboxThreadUiModel

data class InboxUiState(
    val selectedFilter: InboxFilter = InboxFilter.Urgent,
    val groups: List<InboxGroupUiModel> = emptyList()
) {
    val visibleGroups: List<InboxGroupUiModel>
        get() = groups.mapNotNull { group ->
            val filteredItems = group.items.filter { thread ->
                selectedFilter in thread.filters
            }

            filteredItems.takeIf { it.isNotEmpty() }?.let { items ->
                group.copy(items = items)
            }
        }

    val totalUnreadCount: Int
        get() = groups.sumOf { group ->
            group.items.count(InboxThreadUiModel::unread)
        }

    val approvalCount: Int
        get() = groups.sumOf { group ->
            group.items.count { thread ->
                thread.unread && InboxFilter.Approvals in thread.filters
            }
        }

    val mentionCount: Int
        get() = groups.sumOf { group ->
            group.items.count { thread ->
                thread.unread && InboxFilter.Mentions in thread.filters
            }
        }

    val heroMessage: String
        get() = when {
            totalUnreadCount == 0 -> "Inbox is clear. All priority threads have been reviewed."
            approvalCount > 0 && mentionCount > 0 ->
                "$approvalCount approvals and $mentionCount mentions are still waiting on a response."
            approvalCount > 0 ->
                "$approvalCount approvals should be handled before the next delivery handoff."
            mentionCount > 0 ->
                "$mentionCount mentions still need a reply from your queue."
            else ->
                "$totalUnreadCount unread threads are still active in your queue."
        }
}

sealed interface InboxAction {
    data class SelectFilter(val filter: InboxFilter) : InboxAction
    data object FocusPriorityQueue : InboxAction
    data object MarkAllRead : InboxAction
}
