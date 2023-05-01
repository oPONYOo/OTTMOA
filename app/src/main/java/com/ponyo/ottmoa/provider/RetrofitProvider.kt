package com.ponyo.ottmoa.provider


import com.ponyo.ottmoa.data.YoutubeApi
import com.ponyo.ottmoa.data.YoutubeRepository
import com.ponyo.ottmoa.data.YoutubeRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://www.googleapis.com/youtube/v3/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun provideRepository(): YoutubeRepository = YoutubeRepositoryImpl(provideApi())

private fun provideApi(): YoutubeApi = provideRetrofit()
    .create(YoutubeApi::class.java)
