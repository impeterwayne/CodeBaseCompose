package com.genesys.core.model.projects

data class ProjectOverview(
    val name: String,
    val stage: String,
    val dueDate: String,
    val lead: String,
    val summary: String,
    val riskLabel: String,
    val highlighted: Boolean
)
