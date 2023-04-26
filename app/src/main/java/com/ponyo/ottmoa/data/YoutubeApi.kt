package com.ponyo.ottmoa.data

import com.ponyo.ottmoa.data.entity.YoutubeUserInfoSet
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("channels")
    suspend fun getUserInfo(
        @Query("key") key: String = " ",
        @Query("part") part: String = "id, snippet",
        @Query("id") channelId: String = "Netflix",
    ): Result<YoutubeUserInfoSet>
}