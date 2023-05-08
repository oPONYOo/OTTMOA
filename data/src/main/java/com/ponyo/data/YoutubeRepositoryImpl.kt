package com.ponyo.data

import com.ponyo.domain.entity.YoutubeChannelVideos
import com.ponyo.domain.entity.YoutubeUserInfoSet
import com.ponyo.domain.YoutubeRepository

class YoutubeRepositoryImpl(
    private val youtubeApi: YoutubeApi
) : YoutubeRepository, YoutubeApi by youtubeApi {


    override suspend fun getChannelInfo(): YoutubeUserInfoSet =
        youtubeApi.getUserInfo( channelId = "UCiEEF51uRAeZeCo8CJFhGWw")

    override suspend fun getVideoItems(): YoutubeChannelVideos =
        youtubeApi.getChannelVideos(channelId = "UCiEEF51uRAeZeCo8CJFhGWw", order = "date")
}