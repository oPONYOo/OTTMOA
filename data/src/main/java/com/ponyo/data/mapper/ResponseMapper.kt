package com.ponyo.data.mapper

import com.ponyo.data.entity.YoutubeChannelVideosResponse
import com.ponyo.data.entity.YoutubeUserInfoSetResponse
import com.ponyo.domain.entity.YoutubeChannelVideos
import com.ponyo.domain.entity.YoutubeUserInfoSet

object YoutubeUserInfoSetMapper :
    BaseMapper<YoutubeUserInfoSetResponse, YoutubeUserInfoSet> {
    override fun YoutubeUserInfoSetResponse.mapTo(): YoutubeUserInfoSet =
        YoutubeUserInfoSet(
            etag = etag,
            items = items,
            kind = kind,
            pageInfo = pageInfo
        )
}

object YoutubeChannelVideosMapper :
    BaseMapper<YoutubeChannelVideosResponse, YoutubeChannelVideos> {
    override fun YoutubeChannelVideosResponse.mapTo(): YoutubeChannelVideos =
        YoutubeChannelVideos(
            etag = etag,
            items = items,
            kind = kind,
            nextPageToken = nextPageToken,
            pageInfo = pageInfo,
            regionCode = regionCode
        )
}