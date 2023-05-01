package com.ponyo.ottmoa.data

import com.ponyo.ottmoa.BuildConfig
import com.ponyo.ottmoa.data.entity.YoutubeChannelVideos
import com.ponyo.ottmoa.data.entity.YoutubeUserInfoSet
import kotlinx.coroutines.flow.Flow


interface YoutubeRepository {
    suspend fun getChannelInfo(): YoutubeUserInfoSet

    suspend fun getVideoItems(): YoutubeChannelVideos
}

class YoutubeRepositoryImpl(
    private val youtubeApi: YoutubeApi
) : YoutubeRepository, YoutubeApi by youtubeApi {


    override suspend fun getChannelInfo(): YoutubeUserInfoSet =
        youtubeApi.getUserInfo( channelId = "UCiEEF51uRAeZeCo8CJFhGWw")

    override suspend fun getVideoItems(): YoutubeChannelVideos =
        youtubeApi.getChannelVideos(channelId = "UCiEEF51uRAeZeCo8CJFhGWw", order = "date")
}

