package com.genesys.core.database.entity.mapper

import com.genesys.core.database.entity.TemplateCollectionsEntity
import com.genesys.core.model.template.TemplateCollections

object TemplateCollectionsEntityMapper : EntityMapper<List<TemplateCollections>, List<TemplateCollectionsEntity>> {
    override fun asEntity(domain: List<TemplateCollections>): List<TemplateCollectionsEntity> {
        return domain.map { collection ->
            TemplateCollectionsEntity(
                id = collection.id,
                code = collection.code,
                name = collection.name,
                sort = collection.sort,
                templates = collection.templates
            )
        }
    }

    override fun asDomain(entity: List<TemplateCollectionsEntity>): List<TemplateCollections> {
        return entity.map { collectionEntity ->
            TemplateCollections(
                id = collectionEntity.id,
                code = collectionEntity.code,
                name = collectionEntity.name,
                sort = collectionEntity.sort,
                templates = collectionEntity.templates
            )
        }
    }
}

fun List<TemplateCollections>.asEntity(): List<TemplateCollectionsEntity> =
    TemplateCollectionsEntityMapper.asEntity(this)

fun List<TemplateCollectionsEntity>.asDomain(): List<TemplateCollections> =
    TemplateCollectionsEntityMapper.asDomain(this)
