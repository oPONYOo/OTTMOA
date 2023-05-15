package com.ponyo.data.entity

import com.ponyo.domain.entity.ChannelThumbnails
import com.ponyo.domain.entity.Default
import com.ponyo.domain.entity.High
import com.ponyo.domain.entity.Id
import com.ponyo.domain.entity.Localized
import com.ponyo.domain.entity.Medium
import com.ponyo.domain.entity.PageInfo
import com.ponyo.domain.entity.Snippet
import com.ponyo.domain.entity.Thumbnails
import com.ponyo.domain.entity.UserInfoSnippet
import com.ponyo.domain.entity.YoutubeChannelVideo
import com.ponyo.domain.entity.YoutubeUserInfo

data class YoutubeUserInfoSetResponse(
    val etag: String,
    val items: List<YoutubeUserInfo>,
    val kind: String,
    val pageInfo: PageInfo
)

data class YoutubeChannelVideosResponse(
    val etag: String,
    val items: List<YoutubeChannelVideo>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val regionCode: String
)

data class YoutubeChannelVideoResponse(
    val etag: String,
    val id: Id,
    val kind: String,
    val snippet: Snippet
)

data class IdResponse(
    val kind: String,
    val videoId: String
)

data class SnippetResponse(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val liveBroadcastContent: String,
    val publishTime: String,
    val publishedAt: String,
    val thumbnails: ChannelThumbnails,
    val title: String
)

data class ChannelThumbnailsResponse(
    val default: Default,
    val high: High,
    val medium: Medium
)


data class YoutubeUserInfoResponse(
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: UserInfoSnippet
)

data class UserInfoSnippetResponse(
    val country: String,
    val description: String,
    val localized: Localized,
    val publishedAt: String,
    val thumbnails: Thumbnails,
    val title: String
)


data class LocalizedResponse(
    val description: String,
    val title: String
)

data class PageInfoResponse(
    val resultsPerPage: Int,
    val totalResults: Int
)

data class ThumbnailsResponse(
    val default: Default,
    val high: High,
    val medium: Medium
)

data class DefaultResponse(
    val height: Int,
    val url: String,
    val width: Int
)

data class HighResponse(
    val height: Int,
    val url: String,
    val width: Int
)

data class MediumResponse(
    val height: Int,
    val url: String,
    val width: Int
)
