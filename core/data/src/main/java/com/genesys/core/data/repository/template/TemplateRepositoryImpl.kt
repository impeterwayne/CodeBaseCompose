package com.genesys.core.data.repository.template

import com.genesys.core.common.TimeUtils
import com.genesys.core.common.base.ResultFlow
import com.genesys.core.data.mmkv.MMKVData
import com.genesys.core.database.dao.TemplateCollectionsDao
import com.genesys.core.database.entity.mapper.asDomain
import com.genesys.core.database.entity.mapper.asEntity
import com.genesys.core.domain.repository.template.TemplateRepository
import com.genesys.core.model.template.TemplateCollections
import com.genesys.core.network.service.ApiService
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TemplateRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val templateCollectionsDao: TemplateCollectionsDao
) : TemplateRepository {

    override fun getAllTemplates(): Flow<ResultFlow<List<TemplateCollections>>> = flow {
        emit(ResultFlow.Loading())

        val cached = templateCollectionsDao.getAllTemplateCollections().asDomain()
        val shouldFetchFromNetwork =
            TimeUtils.isTimeRangeValidForFetchingData(MMKVData.lastFetchTemplateTime) || cached.isEmpty()

        if (!shouldFetchFromNetwork) {
            emit(ResultFlow.Success(cached))
        } else {
            val response = apiService.getAITemplates()
            response.suspendOnSuccess {
                val collections = data.data
                templateCollectionsDao.clearAllTemplateCollections()
                templateCollectionsDao.insertTemplateCollections(collections.asEntity())
                MMKVData.lastFetchTemplateTime = System.currentTimeMillis()
                emit(ResultFlow.Success(collections))
            }.suspendOnFailure {
                if (cached.isNotEmpty()) {
                    // Network failed but we have cache — serve stale data
                    emit(ResultFlow.Success(cached))
                } else {
                    emit(ResultFlow.Error(message()))
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}
