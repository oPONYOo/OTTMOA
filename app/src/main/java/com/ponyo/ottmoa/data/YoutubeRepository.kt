package com.ponyo.ottmoa.data

import com.ponyo.ottmoa.data.entity.YoutubeUserInfoSet
import kotlinx.coroutines.flow.Flow


interface YoutubeRepository {
    suspend fun getChannelInfo(): Result<YoutubeUserInfoSet>
}

class YoutubeRepositoryImpl(
    private val youtubeApi: YoutubeApi
) : YoutubeRepository, YoutubeApi by youtubeApi {


    override suspend fun getChannelInfo(): Result<YoutubeUserInfoSet> =
        youtubeApi.getUserInfo(key = "", part = "", channelId = "")
}

