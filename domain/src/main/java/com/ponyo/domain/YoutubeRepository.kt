package com.ponyo.domain

import com.ponyo.domain.entity.YoutubeChannelVideos
import com.ponyo.domain.entity.YoutubeUserInfoSet


interface YoutubeRepository {
    suspend fun getChannelInfo(channelId: String): YoutubeUserInfoSet

    suspend fun getVideoItems(channelId: String): YoutubeChannelVideos
}



