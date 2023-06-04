package com.ponyo.presentation.uistate

import com.ponyo.domain.entity.YoutubeUserInfoSet
import com.ponyo.presentation.model.Channel

data class ChannelUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val channelItems: List<Channel> = emptyList()
) {
    companion object {
        val Uninitialized =
            ChannelUiState(
                isLoading = true,
                isError = false,
                channelItems = emptyList()
            )
    }

}

fun List<YoutubeUserInfoSet>.toUiState(): List<Channel> =
    map { it.toUiState() }

fun YoutubeUserInfoSet.toUiState(): Channel =
    Channel(
        thumbnail = items[0].snippet.thumbnails.default.url,
        channelName = items[0].snippet.title,
        recentDate = items[0].snippet.publishedAt
    )



