package com.genesys.core.database.entity.mapper

import com.genesys.core.database.entity.PokedexCollectionsEntity
import com.genesys.core.model.pokedex.PokedexCollections

object PokedexCollectionsEntityMapper : EntityMapper<List<PokedexCollections>, List<PokedexCollectionsEntity>> {
    override fun asEntity(domain: List<PokedexCollections>): List<PokedexCollectionsEntity> {
        return domain.map { collection ->
            PokedexCollectionsEntity(
                id = collection.id,
                code = collection.code,
                name = collection.name,
                sort = collection.sort,
                pokemon = collection.pokemon
            )
        }
    }

    override fun asDomain(entity: List<PokedexCollectionsEntity>): List<PokedexCollections> {
        return entity.map { collectionEntity ->
            PokedexCollections(
                id = collectionEntity.id,
                code = collectionEntity.code,
                name = collectionEntity.name,
                sort = collectionEntity.sort,
                pokemon = collectionEntity.pokemon
            )
        }
    }
}

fun List<PokedexCollections>.asEntity(): List<PokedexCollectionsEntity> =
    PokedexCollectionsEntityMapper.asEntity(this)

fun List<PokedexCollectionsEntity>.asDomain(): List<PokedexCollections> =
    PokedexCollectionsEntityMapper.asDomain(this)
