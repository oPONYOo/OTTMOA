package com.ponyo.ottmoa.presentation

import androidx.lifecycle.ViewModel
import com.ponyo.ottmoa.data.YoutubeRepository

class MainViewModel(
    private val youtubeRepository: YoutubeRepository,
): ViewModel() {

    init {

    }

    private suspend fun fetchChannels() {
        youtubeRepository.getChannelInfo()
    }
}