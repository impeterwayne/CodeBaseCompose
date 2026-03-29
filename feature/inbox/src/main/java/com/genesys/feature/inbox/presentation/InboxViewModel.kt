package com.genesys.feature.inbox.presentation

import androidx.lifecycle.ViewModel
import com.genesys.core.model.inbox.InboxFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class InboxViewModel @Inject constructor() : ViewModel(), ContainerHost<InboxUiState, Nothing> {

    override val container = container<InboxUiState, Nothing>(
        InboxUiState(
            groups = sampleInboxGroups
        )
    )

    fun onAction(action: InboxAction) {
        when (action) {
            is InboxAction.SelectFilter -> selectFilter(action.filter)
            InboxAction.FocusPriorityQueue -> focusPriorityQueue()
            InboxAction.MarkAllRead -> markAllRead()
        }
    }

    private fun selectFilter(filter: InboxFilter) = intent {
        reduce {
            state.copy(selectedFilter = filter)
        }
    }

    private fun focusPriorityQueue() = intent {
        reduce {
            state.copy(selectedFilter = InboxFilter.Urgent)
        }
    }

    private fun markAllRead() = intent {
        reduce {
            state.copy(
                groups = state.groups.map { group ->
                    group.copy(
                        items = group.items.map { thread ->
                            thread.copy(unread = false)
                        }
                    )
                }
            )
        }
    }
}
