package com.ponyo.presentation.uistate

import com.ponyo.domain.entity.YoutubeChannelVideo
import com.ponyo.presentation.model.Feed

data class FeedUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val feedItems: List<Feed> = emptyList()
) {
    companion object {
        val Uninitialized = FeedUiState(
            isLoading = false,
            isError = false,
            feedItems = emptyList()
        )
    }
}

fun List<YoutubeChannelVideo>.toUiState(): List<Feed> =
    map { it.toUiState() }

fun YoutubeChannelVideo.toUiState(): Feed =
    Feed(
        thumbnail = snippet.thumbnails.high.url,
        date = snippet.publishedAt,
        description = snippet.description,
        bookMarked = false
    )
