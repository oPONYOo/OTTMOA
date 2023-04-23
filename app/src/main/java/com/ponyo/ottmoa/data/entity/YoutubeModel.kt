package com.ponyo.ottmoa.data.entity

data class YoutubeUserInfoSet(
    val etag: String,
    val items: List<YoutubeUserInfo>,
    val kind: String,
    val pageInfo: PageInfo
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
