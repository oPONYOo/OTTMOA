package com.ponyo.domain.usecase

import com.ponyo.domain.LocalInfoRepository
import com.ponyo.domain.YoutubeRepository
import com.ponyo.domain.entity.LocalInfo
import com.ponyo.domain.entity.YoutubeChannelVideo

open class GetFeedUseCase(
    private val youtubeRepository: YoutubeRepository
    ) {
    open suspend operator fun invoke(vararg channelIdList: String): List<YoutubeChannelVideo> {
        val list: ArrayList<YoutubeChannelVideo> = ArrayList()
        channelIdList.forEach { channelId ->
            val response = youtubeRepository.getVideoItems(channelId).items
            list.addAll(response)
        }


        return list.sortedByDescending { it.snippet.publishedAt }.toList()
    }
}