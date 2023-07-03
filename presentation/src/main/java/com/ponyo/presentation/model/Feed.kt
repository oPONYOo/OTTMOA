package com.ponyo.presentation.model

data class Feed(
    val videoId: String,
    val channelTitle: String,
    val thumbnail: String,
    val date: String,
    val description: String? = null,
    val bookMarked: Boolean
)
