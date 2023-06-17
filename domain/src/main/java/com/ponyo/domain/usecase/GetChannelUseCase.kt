package com.ponyo.domain.usecase

import com.ponyo.domain.YoutubeRepository
import com.ponyo.domain.entity.YoutubeUserInfoSet

open class GetChannelUseCase(
    private val youtubeRepository: YoutubeRepository
) {
    open suspend operator fun invoke(vararg channelIdList: String): List<YoutubeUserInfoSet> {
        val list: ArrayList<YoutubeUserInfoSet> = ArrayList(channelIdList.size)
        channelIdList.forEach { channelId ->
            val response = youtubeRepository.getChannelInfo(channelId)
            list.add(response)
        }
        return list.toList()
    }


}