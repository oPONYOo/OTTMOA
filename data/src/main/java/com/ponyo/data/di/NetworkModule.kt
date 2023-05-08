package com.ponyo.data.di

import com.ponyo.data.YoutubeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class ApiService

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    private fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/youtube/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @ApiService
    @Provides
    @Singleton
    fun provideApi(): YoutubeApi = provideRetrofit()
        .create(YoutubeApi::class.java)
}