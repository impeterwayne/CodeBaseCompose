package com.genesys.core.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.genesys.core.model.template.Template
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class TemplateCollectionsEntity(
    @PrimaryKey
    var id: String = "",
    var code: String = "",
    var name: String = "",
    var sort: Int = 0,
    var templates: List<Template> = listOf()
) : Parcelable
