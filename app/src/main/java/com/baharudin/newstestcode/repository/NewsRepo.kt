package com.baharudin.newstestcode.repository

import com.baharudin.newstestcode.data.remote.model.NewsResponse
import retrofit2.Response

interface NewsRepo {
    suspend fun getHealthCategory(countryCode: String, category : String) : Response<NewsResponse>
}