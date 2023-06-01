package com.ponyo.data

import android.util.Log
import com.ponyo.data.di.ApiService
import com.ponyo.data.mapper.YoutubeChannelVideosMapper.mapTo
import com.ponyo.data.mapper.YoutubeUserInfoSetMapper.mapTo
import com.ponyo.domain.entity.YoutubeChannelVideos
import com.ponyo.domain.entity.YoutubeUserInfoSet
import com.ponyo.domain.YoutubeRepository
import javax.inject.Inject

class YoutubeRepositoryImpl @Inject constructor(
    @ApiService private val youtubeApi: YoutubeApi
) : YoutubeRepository, YoutubeApi by youtubeApi {


    override suspend fun getChannelInfo(channelId: String): YoutubeUserInfoSet {
        val response = runCatching {
            youtubeApi.getUserInfo(channelId = channelId)
        }
        Log.e("YoutubeUserInfoSet", "$response")
        return response.getOrThrow().mapTo()
    }

    override suspend fun getVideoItems(channelId: String): YoutubeChannelVideos {
        val response = runCatching {
            youtubeApi.getChannelVideos(
                channelId = channelId,
                order = "date"
            )
        }
        Log.e("YoutubeChannelVideos", "$response")
        return response.getOrThrow().mapTo()
    }
}