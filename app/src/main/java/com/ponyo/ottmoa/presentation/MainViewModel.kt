package com.ponyo.ottmoa.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ponyo.ottmoa.data.YoutubeRepository
import com.ponyo.ottmoa.data.entity.YoutubeUserInfo
import com.ponyo.ottmoa.data.entity.YoutubeUserInfoSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val youtubeRepository: YoutubeRepository,
): ViewModel() {

    init {
    }

    private val _channelInfo = MutableLiveData<YoutubeUserInfoSet>()
    val channelInfo: LiveData<YoutubeUserInfoSet> get() = _channelInfo
     fun fetchChannels() {
         viewModelScope.launch {
             val response = youtubeRepository.getChannelInfo()
             if (response.isSuccess) _channelInfo.value = response.getOrNull()
         }

    }
}