package com.ponyo.presentation.uistate

import com.ponyo.domain.entity.YoutubeChannelVideo
import com.ponyo.presentation.model.Feed

data class FeedUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isRefreshing: Boolean = false,
    val feedItems: List<Feed> = emptyList()
) {
    companion object {
        val Uninitialized = FeedUiState(
            isLoading = true,
            isError = false,
            isRefreshing = false,
            feedItems = emptyList()
        )
    }
}

fun List<YoutubeChannelVideo>.toUiState(): List<Feed> =
    map { it.toUiState() }

fun YoutubeChannelVideo.toUiState(): Feed =
    Feed(
        videoId = id.videoId,
        channelTitle = snippet.channelTitle,
        thumbnail = snippet.thumbnails.high.url,
        date = snippet.publishedAt,
        description = snippet.description,
        bookMarked = false,
    )
