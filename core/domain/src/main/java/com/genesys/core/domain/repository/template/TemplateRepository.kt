package com.genesys.core.domain.repository.template

import com.genesys.core.common.base.Result
import com.genesys.core.model.template.TemplateCollections
import kotlinx.coroutines.flow.Flow

interface TemplateRepository {
    fun getAllTemplates(): Flow<Result<List<TemplateCollections>>>
}
