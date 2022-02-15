package com.baharudin.newstestcode.di

import com.baharudin.newstestcode.data.remote.api.NewsApi
import com.baharudin.newstestcode.repository.NewsRepo
import com.baharudin.newstestcode.repository.NewsRepositoryImpl
import com.baharudin.newstestcode.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsApi::class.java)
    }
    @Provides
    @Singleton
    fun provideNewsRepo(
        newsApi: NewsApi
    ) : NewsRepo {
        return NewsRepositoryImpl(
            newsApi
        )
    }
}