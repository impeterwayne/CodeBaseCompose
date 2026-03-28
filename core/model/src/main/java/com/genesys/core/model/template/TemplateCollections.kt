package com.genesys.core.model.template

import android.os.Parcelable
import com.squareup.moshi.Json
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class TemplateCollections(
    @Json(name = "code")
    val code: String = "",
    @Json(name = "id")
    val id: String = "",
    @Json(name = "items")
    val templates: List<Template> = listOf(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "sort")
    val sort: Int = 0
) : Parcelable
