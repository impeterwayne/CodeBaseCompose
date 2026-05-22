package com.genesys.core.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.genesys.core.model.pokedex.Pokemon
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class PokedexCollectionsEntity(
    @PrimaryKey
    var id: String = "",
    var code: String = "",
    var name: String = "",
    var sort: Int = 0,
    var pokemon: List<Pokemon> = listOf()
) : Parcelable
