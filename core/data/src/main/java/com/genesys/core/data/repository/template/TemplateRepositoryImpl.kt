package com.genesys.core.data.repository.template

import android.R.attr.data
import android.R.id.message
import com.genesys.core.common.TimeUtils
import com.genesys.core.common.base.Result
import com.genesys.core.data.mmkv.MMKVData
import com.genesys.core.database.dao.TemplateCollectionsDao
import com.genesys.core.database.entity.mapper.asDomain
import com.genesys.core.database.entity.mapper.asEntity
import com.genesys.core.domain.repository.template.TemplateRepository
import com.genesys.core.model.template.Template
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

    override fun getAllTemplates(): Flow<Result<List<TemplateCollections>>> = flow {
        emit(Result.Loading())

        val fakeTemplates = listOf(
            Template(
                id = "t1",
                name = "Fake Template 1",
                premium = false,
                ratio = "1:1",
                thumbnail = "https://picsum.photos/200/200"
            ),
            Template(
                id = "t2",
                name = "Fake Template 2",
                premium = true,
                ratio = "16:9",
                thumbnail = "https://picsum.photos/400/225"
            ),
            Template(
                id = "t3",
                name = "Fake Template 3",
                premium = false,
                ratio = "9:16",
                thumbnail = "https://picsum.photos/225/400"
            )
        )
        val fakeCollections = listOf(
            TemplateCollections(
                id = "c1",
                name = "Trending",
                templates = fakeTemplates,
                sort = 1
            ),
            TemplateCollections(
                id = "c2",
                name = "New Arrivals",
                templates = fakeTemplates,
                sort = 2
            )
        )
        emit(Result.Success(fakeCollections))
//
//        val cached = templateCollectionsDao.getAllTemplateCollections().asDomain()
//        val shouldFetchFromNetwork =
//            TimeUtils.isTimeRangeValidForFetchingData(MMKVData.lastFetchTemplateTime) || cached.isEmpty()
//
//        if (!shouldFetchFromNetwork) {
//            emit(Result.Success(cached))
//        } else {
//            val response = apiService.getAITemplates()
//            response.suspendOnSuccess {
//                val collections = data.data
//                templateCollectionsDao.clearAllTemplateCollections()
//                templateCollectionsDao.insertTemplateCollections(collections.asEntity())
//                MMKVData.lastFetchTemplateTime = System.currentTimeMillis()
//                emit(Result.Success(collections))
//            }.suspendOnFailure {
//                if (cached.isNotEmpty()) {
//                    // Network failed but we have cache — serve stale data
//                    emit(Result.Success(cached))
//                } else {
//                    emit(Result.Error(message()))
//                }
//            }
//        }
    }.flowOn(Dispatchers.IO)
}
