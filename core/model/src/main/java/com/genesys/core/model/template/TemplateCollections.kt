package com.genesys.core.model.template

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TemplateCollections(
    val code: String = "",
    val id: String = "",
    val templates: List<Template> = listOf(),
    val name: String = "",
    val sort: Int = 0
) : Parcelable
