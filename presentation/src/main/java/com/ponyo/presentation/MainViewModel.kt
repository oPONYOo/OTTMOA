package com.ponyo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ponyo.domain.YoutubeRepository
import com.ponyo.domain.usecase.GetChannelUseCase
import com.ponyo.domain.usecase.GetFeedUseCase
import com.ponyo.presentation.uistate.ChannelUiState
import com.ponyo.presentation.uistate.FeedUiState
import com.ponyo.presentation.uistate.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getChannelUseCase: GetChannelUseCase,
    private val getFeedUseCase: GetFeedUseCase
) : ViewModel() {

    init {
        fetchChannels()
        fetchFeeds()
    }

    private val _channelUiState = MutableStateFlow(ChannelUiState.Uninitialized)
    val channelUiState: StateFlow<ChannelUiState> = _channelUiState.asStateFlow()

    private val _feedUiState = MutableStateFlow(FeedUiState.Uninitialized)
    val feedUiState: StateFlow<FeedUiState> = _feedUiState.asStateFlow()

    private fun fetchChannels() {
        viewModelScope.launch {
            val response = getChannelUseCase(NETFLIX_CHANNEL_ID, WATCHA_CHANNEL_ID)
            _channelUiState.value =
                channelUiState.value.copy(
                    isLoading = false,
                    channelItems = response.toUiState()
                )
        }

    }

    private fun fetchFeeds() {
        viewModelScope.launch {
            val response = getFeedUseCase(NETFLIX_CHANNEL_ID, WATCHA_CHANNEL_ID)
            _feedUiState.value =
                feedUiState.value.copy(
                    isLoading = false,
                    feedItems = response.toUiState()
                )
        }
    }

    companion object {
        const val NETFLIX_CHANNEL_ID = "UCiEEF51uRAeZeCo8CJFhGWw"
        const val WATCHA_CHANNEL_ID = "UCgmmc51A3qyAR3MvVX-rzCQ"
    }
}