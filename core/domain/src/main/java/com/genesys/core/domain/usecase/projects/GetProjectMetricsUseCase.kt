package com.genesys.core.domain.usecase.projects

import com.genesys.core.model.projects.ProjectMetric
import javax.inject.Inject

class GetProjectMetricsUseCase @Inject constructor() {
    operator fun invoke(): List<ProjectMetric> {
        return listOf(
            ProjectMetric(label = "Active", value = "06"),
            ProjectMetric(label = "In review", value = "03"),
            ProjectMetric(label = "Due this week", value = "09")
        )
    }
}
