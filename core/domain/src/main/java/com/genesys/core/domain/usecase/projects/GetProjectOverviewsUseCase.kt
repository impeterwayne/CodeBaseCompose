package com.genesys.core.domain.usecase.projects

import com.genesys.core.model.projects.ProjectOverview
import javax.inject.Inject

class GetProjectOverviewsUseCase @Inject constructor() {
    operator fun invoke(): List<ProjectOverview> {
        return listOf(
            ProjectOverview(
                name = "Spring Campaign Launch",
                stage = "Production",
                dueDate = "Due Apr 02",
                lead = "Lead: Amelia",
                summary = "Final asset approvals and export presets are being locked for the launch batch.",
                riskLabel = "On track",
                highlighted = true
            ),
            ProjectOverview(
                name = "Creator Partnership Deck",
                stage = "Feedback",
                dueDate = "Due Apr 04",
                lead = "Lead: Jordan",
                summary = "Sales notes are merged. Legal review and pricing slides still need sign-off before handoff.",
                riskLabel = "Needs review",
                highlighted = false
            ),
            ProjectOverview(
                name = "Template Refresh Q2",
                stage = "Planning",
                dueDate = "Due Apr 08",
                lead = "Lead: Priya",
                summary = "The design system audit is complete and the first set of replacement layouts is queued.",
                riskLabel = "Research",
                highlighted = false
            )
        )
    }
}
