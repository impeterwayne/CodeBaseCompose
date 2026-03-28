package com.genesys.core.common.extension

fun String.isValidUrl(): Boolean {
    val regex = Regex("^(https?://)?(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+(/.*)?$")
    return regex.matches(this)
}

fun String.formatUrl(): String {
    var url = this.trim()
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        url = "https://$url"
    }
    return url
}
