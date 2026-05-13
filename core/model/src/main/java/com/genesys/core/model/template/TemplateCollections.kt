package com.genesys.core.model.template

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class TemplateCollections(
    @SerializedName("code")
    val code: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("items")
    val templates: List<Template> = listOf(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("sort")
    val sort: Int = 0
) : Parcelable
