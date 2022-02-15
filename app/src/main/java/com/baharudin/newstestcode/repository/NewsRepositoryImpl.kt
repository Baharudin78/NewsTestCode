package com.baharudin.newstestcode.repository

import com.baharudin.newstestcode.data.remote.api.NewsApi
import com.baharudin.newstestcode.data.remote.model.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi : NewsApi
) :NewsRepo{
    override suspend fun getHealthCategory(
        countryCode: String,
        category: String
    ): Response<NewsResponse> =
        newsApi.getHealthCategory()
}