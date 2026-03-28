package com.genesys.core.common

/**
 * @author : CuongNK
 * @created : 5/21/2025
 *
 * TimeUtils provides time-range validation for data fetching.
 * Note: The lastFetchTime must be provided by the caller (e.g., from MMKVData in :core:data).
 **/
object TimeUtils {
    private const val ONE_DAY_IN_MILLI_SEC: Long = 24 * 60 * 60 * 1000 // 1 day in milliseconds

    /**
     * Check if enough time has passed since the last fetch.
     * @param lastFetchTime The timestamp of the last fetch in milliseconds
     * @return true if more than one day has passed since lastFetchTime
     */
    fun isTimeRangeValidForFetchingData(lastFetchTime: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        return (currentTime - lastFetchTime) > ONE_DAY_IN_MILLI_SEC
    }
}
