package com.ponyo.data

import com.ponyo.data.entity.YoutubeChannelVideosResponse
import com.ponyo.data.entity.YoutubeUserInfoSetResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("channels")
    suspend fun getUserInfo(
        @Query("key") key: String = BuildConfig.KEY,
        @Query("part") part: String = "id, snippet",
        @Query("id") channelId: String,
    ): YoutubeUserInfoSetResponse

    @GET("search")
    suspend fun getChannelVideos(
        @Query("key") key: String = BuildConfig.KEY,
        @Query("part") part: String = "snippet",
        @Query("channelId") channelId: String,
        @Query("order") order: String,
        @Query("maxResults") maxResults: Int = 10,
    ): YoutubeChannelVideosResponse
}