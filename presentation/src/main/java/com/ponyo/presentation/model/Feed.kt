package com.ponyo.presentation.model

data class Feed(
    val thumbnail: String,
    val date: Int,
    val description: String? = null,
    val bookMarked: Boolean
)
