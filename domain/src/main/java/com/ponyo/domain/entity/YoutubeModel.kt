package com.ponyo.domain.entity

data class YoutubeUserInfoSet(
    val etag: String,
    val items: List<YoutubeUserInfo>,
    val kind: String,
    val pageInfo: PageInfo
)

data class YoutubeChannelVideos(
    val etag: String,
    val items: List<YoutubeChannelVideo>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val regionCode: String
)

data class YoutubeChannelVideo(
    val etag: String,
    val id: Id,
    val kind: String,
    val snippet: Snippet
)

data class Id(
    val kind: String,
    val videoId: String
)

data class Snippet(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val liveBroadcastContent: String,
    val publishTime: String,
    val publishedAt: String,
    val thumbnails: ChannelThumbnails,
    val title: String
)

data class ChannelThumbnails(
    val default: Default,
    val high: High,
    val medium: Medium
)


data class YoutubeUserInfo(
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: UserInfoSnippet
)

data class UserInfoSnippet(
    val country: String,
    val description: String,
    val localized: Localized,
    val publishedAt: String,
    val thumbnails: Thumbnails,
    val title: String

)

data class Statistics(
    val viewCount: String,
    val likeCount: String,
    val dislikeCount: String,
    val favoriteCount: String,
    val commentCount: String
)

data class Localized(
    val description: String,
    val title: String
)

data class PageInfo(
    val resultsPerPage: Int,
    val totalResults: Int
)

data class Thumbnails(
    val default: Default,
    val high: High,
    val medium: Medium
)

data class ContentDetails(
    val duration: String,
    val dimension: String,
    val definition: String,
    val caption: String,
    val licensedContent: Boolean,
    val regionRestriction: RegionRestriction
)

data class RegionRestriction(
    val allowed: List<String>,
    val blocked: List<String>
)


data class Default(
    val height: Int,
    val url: String,
    val width: Int
)

data class High(
    val height: Int,
    val url: String,
    val width: Int
)

data class Medium(
    val height: Int,
    val url: String,
    val width: Int
)
