package com.baharudin.newstestcode.data.remote.api

import com.baharudin.newstestcode.data.remote.model.NewsResponse
import com.baharudin.newstestcode.util.Constant.API_KEY
import com.baharudin.newstestcode.util.Constant.API_VERSION
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("$API_VERSION/top-headlines")
    suspend fun getHealthCategory(
        @Query("country")
        countryCode : String = "id",
        @Query("category")
        category : String = "health",
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>
}