package com.genesys.codebase

import android.app.Application
import com.genesys.core.datastore.MMKVData
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @author : CuongNK
 * @created : 9/1/2025
 **/

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKVData.MyMMKV.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
