package com.genesys.core.model.template

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class Template(
    @SerializedName("categoryDocumentId")
    val categoryDocumentId: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("premium")
    val premium: Boolean = false,
    @SerializedName("ratio")
    val ratio: String = "1:1",
    @SerializedName("resource")
    val resource: String = "",
    @SerializedName("sort")
    val sort: Int = 0,
    @SerializedName("thumbnail")
    val thumbnail: String = ""
) : Parcelable
