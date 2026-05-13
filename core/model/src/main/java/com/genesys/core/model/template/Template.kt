package com.genesys.core.model.template

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Template(
    val categoryDocumentId: String = "",
    val id: String = "",
    val name: String = "",
    val premium: Boolean = false,
    val ratio: String = "1:1",
    val resource: String = "",
    val sort: Int = 0,
    val thumbnail: String = ""
) : Parcelable
