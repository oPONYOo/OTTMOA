package com.ponyo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ponyo.domain.entity.YoutubeChannelVideos
import com.ponyo.domain.entity.YoutubeUserInfoSet
import com.ponyo.domain.YoutubeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val youtubeRepository: YoutubeRepository,
) : ViewModel() {

    init {
    }

    private val _channelInfo = MutableLiveData<YoutubeUserInfoSet>()
    val channelInfo: LiveData<YoutubeUserInfoSet> get() = _channelInfo

    private val _videoItems = MutableLiveData<YoutubeChannelVideos>()
    val videoItems: LiveData<YoutubeChannelVideos> get() = _videoItems

    fun fetchChannels() {
        viewModelScope.launch {
            val response = youtubeRepository.getChannelInfo()
            _channelInfo.value = response
        }

    }

    fun fetchVideoItems() {
        viewModelScope.launch {
            val response = youtubeRepository.getVideoItems()
            _videoItems.value = response
        }
    }
}