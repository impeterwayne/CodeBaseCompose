package com.genesys.codebase

import android.annotation.SuppressLint
import android.app.Application
import android.media.MediaDrm
import android.os.Build
import android.provider.Settings
import android.util.Base64
import com.genesys.core.datastore.MMKVData
import com.hjq.device.compat.DeviceBrand
import com.hjq.device.compat.DeviceMarketName
import com.hjq.device.compat.DeviceOs
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.security.MessageDigest
import java.util.UUID

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
        logDeviceUniqueId()
        logAndroidId()
        logDeviceCompatProperties()
    }

    private fun logDeviceUniqueId() {
        var mediaDrm: MediaDrm? = null
        try {
            if (!MediaDrm.isCryptoSchemeSupported(WIDEVINE_UUID)) {
                Timber.tag(TAG_MEDIA_DRM).w("Widevine DRM scheme is not supported on this device")
                return
            }
            mediaDrm = MediaDrm(WIDEVINE_UUID)
            val uniqueIdBytes = mediaDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID)
            val hexString = uniqueIdBytes.joinToString("") { "%02x".format(it) }
            val base64String = Base64.encodeToString(uniqueIdBytes, Base64.NO_WRAP)
            Timber.tag(TAG_MEDIA_DRM).d(
                "PROPERTY_DEVICE_UNIQUE_ID (size=%d): hex=%s, base64=%s",
                uniqueIdBytes.size,
                hexString,
                base64String
            )
            Timber.tag(TAG_MEDIA_DRM).d(
                "MediaDrm Properties -> vendor=%s, version=%s, description=%s, algorithms=%s, securityLevel=%s, systemId=%s",
                runCatching { mediaDrm.getPropertyString(MediaDrm.PROPERTY_VENDOR) }.getOrNull(),
                runCatching { mediaDrm.getPropertyString(MediaDrm.PROPERTY_VERSION) }.getOrNull(),
                runCatching { mediaDrm.getPropertyString(MediaDrm.PROPERTY_DESCRIPTION) }.getOrNull(),
                runCatching { mediaDrm.getPropertyString(MediaDrm.PROPERTY_ALGORITHMS) }.getOrNull(),
                runCatching { mediaDrm.getPropertyString("securityLevel") }.getOrNull(),
                runCatching { mediaDrm.getPropertyString("systemId") }.getOrNull()
            )
        } catch (e: Exception) {
            Timber.tag(TAG_MEDIA_DRM).e(e, "Failed to retrieve PROPERTY_DEVICE_UNIQUE_ID")
        } finally {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                mediaDrm?.close()
            } else {
                @Suppress("DEPRECATION")
                mediaDrm?.release()
            }
        }
    }

    @SuppressLint("HardwareIds")
    private fun logAndroidId() {
        try {
            val androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
            if (androidId.isNullOrBlank()) {
                Timber.tag(TAG_ANDROID_ID).w("Settings.Secure.ANDROID_ID is null or blank")
                return
            }
            val sha256Bytes = MessageDigest.getInstance("SHA-256")
                .digest(androidId.toByteArray(Charsets.UTF_8))
            val sha256Hex = sha256Bytes.joinToString("") { "%02x".format(it) }
            val sha256Base64 = Base64.encodeToString(sha256Bytes, Base64.NO_WRAP)
            Timber.tag(TAG_ANDROID_ID).d(
                "ANDROID_ID: raw=%s, sha256Hex=%s, sha256Base64=%s",
                androidId,
                sha256Hex,
                sha256Base64
            )
        } catch (e: Exception) {
            Timber.tag(TAG_ANDROID_ID).e(e, "Failed to retrieve ANDROID_ID")
        }
    }

    private fun logDeviceCompatProperties() {
        try {
            Timber.tag(TAG_DEVICE_COMPAT).d(
                "DeviceCompat Info -> Brand: %s, MarketName: %s, OS: %s (version=%s, bigCode=%d)",
                DeviceBrand.getBrandName(),
                DeviceMarketName.getMarketName(this),
                DeviceOs.getOsName(),
                DeviceOs.getOsVersionName(),
                DeviceOs.getOsBigVersionCode()
            )
        } catch (e: Exception) {
            Timber.tag(TAG_DEVICE_COMPAT).e(e, "Failed to retrieve DeviceCompat properties")
        }
    }

    companion object {
        private const val TAG_MEDIA_DRM = "MediaDrm"
        private const val TAG_ANDROID_ID = "AndroidId"
        private const val TAG_DEVICE_COMPAT = "DeviceCompat"

        private val WIDEVINE_UUID = UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed")
    }
}
