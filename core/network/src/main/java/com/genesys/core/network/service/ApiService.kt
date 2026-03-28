package com.genesys.core.network.service

import com.genesys.core.network.response.ResponseAITemplate
import com.skydoves.sandwich.ApiResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface ApiService {

    @GET("content/api/and-remove-background/home")
    suspend fun getAITemplates(): ApiResponse<ResponseAITemplate>

    @Streaming
    @GET
    suspend fun downloadFile(@Url fileUrl: String): ResponseBody

}
