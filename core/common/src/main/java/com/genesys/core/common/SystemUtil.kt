package com.genesys.core.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import java.util.*

object SystemUtil {
    private var myLocale: Locale? = null

    private fun changeLang(lang: String?, context: Context) {
        if (lang.equals("", ignoreCase = true)) return
        myLocale = Locale(lang)
        saveLocale(context, lang)
        Locale.setDefault(myLocale)
        val config = Configuration()
        config.locale = myLocale
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    fun setLocale(context: Context) {
        val language = getPreLanguage(context)
        if (language == "") {
            val config = Configuration()
            val locale = Locale.getDefault()
            Locale.setDefault(locale)
            config.locale = locale
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        } else {
            changeLang(language, context)
        }
    }

    val languageApp: List<String>
        get() {
            val languages: MutableList<String> = ArrayList()
            languages.add("ar") // Arabic
            languages.add("de") // German
            languages.add("en") // English
            languages.add("es") // Spanish
            languages.add("fil") // Filipino
            languages.add("fr") // French
            languages.add("hi") // Hindi
            languages.add("hr") // Croatian
            languages.add("in") // Indonesian
            languages.add("it") // Italian
            languages.add("ko") // Korean
            languages.add("ja") // Japanese
            languages.add("ms") // Malay
            languages.add("nl") // Dutch
            languages.add("pl") // Polish
            languages.add("pt") // Portuguese
            languages.add("ru") // Russian
            languages.add("sr") // Serbian
            languages.add("sv") // Swedish
            languages.add("tr") // Turkish
            languages.add("vi") // Vietnamese
            languages.add("zh") // Chinese
            languages.add("uk") // Ukrainian
            languages.add("th") // Thai
            return languages
        }

    private fun saveLocale(context: Context, lang: String?) {
        setPreLanguage(context, lang)
    }

    @SuppressLint("ObsoleteSdkInt")
    fun getPreLanguage(mContext: Context): String? {
        val preferences = mContext.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        return preferences.getString("KEY_LANGUAGE", "en")
    }

    fun setPreLanguage(context: Context, language: String?) {
        if (language == null || language == "") {
            return
        } else {
            val preferences = context.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
            preferences.edit().putString("KEY_LANGUAGE", language).apply()
        }
    }
}
