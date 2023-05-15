package com.ponyo.data

import com.ponyo.data.mapper.YoutubeChannelVideosMapper.mapTo
import com.ponyo.data.mapper.YoutubeUserInfoSetMapper.mapTo
import com.ponyo.domain.entity.YoutubeChannelVideos
import com.ponyo.domain.entity.YoutubeUserInfoSet
import com.ponyo.domain.YoutubeRepository

class YoutubeRepositoryImpl(
    private val youtubeApi: YoutubeApi
) : YoutubeRepository, YoutubeApi by youtubeApi {


    override suspend fun getChannelInfo(channelId: String): YoutubeUserInfoSet =
        youtubeApi.getUserInfo( channelId = channelId).mapTo()

    override suspend fun getVideoItems(channelId: String): YoutubeChannelVideos =
        youtubeApi.getChannelVideos(channelId = channelId, order = "date").mapTo()
}