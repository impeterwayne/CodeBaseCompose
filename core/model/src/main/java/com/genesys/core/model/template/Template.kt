package com.genesys.core.model.template

import android.os.Parcelable
import com.squareup.moshi.Json
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class Template(
    @Json(name = "categoryDocumentId")
    val categoryDocumentId: String = "",
    @Json(name = "id")
    val id: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "premium")
    val premium: Boolean = false,
    @Json(name = "ratio")
    val ratio: String = "1:1",
    @Json(name = "resource")
    val resource: String = "",
    @Json(name = "sort")
    val sort: Int = 0,
    @Json(name = "thumbnail")
    val thumbnail: String = ""
) : Parcelable
