package com.ponyo.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ponyo.domain.usecase.GetChannelUseCase
import com.ponyo.domain.usecase.GetFeedUseCase
import com.ponyo.presentation.uistate.ChannelUiState
import com.ponyo.presentation.uistate.FeedUiState
import com.ponyo.presentation.uistate.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

    private val _errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()


    private fun fetchChannels() {
        viewModelScope.launch {
            try {
                val response = getChannelUseCase(NETFLIX_CHANNEL_ID, WATCHA_CHANNEL_ID)
                _channelUiState.update {
                    channelUiState.value.copy(
                        isLoading = false,
                        channelItems = response.toUiState()
                    )
                }

            } catch (e: Exception) {
                Log.e("ChannelException", "$e")
                when (e) {
                    is java.net.UnknownHostException -> _errorMessage.update {
                        e.message
                    }
                }
                _channelUiState.update {
                    channelUiState.value.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }

        }

    }

    private fun fetchFeeds() {
        viewModelScope.launch {
            try {
                val response = getFeedUseCase(NETFLIX_CHANNEL_ID, WATCHA_CHANNEL_ID)
                _feedUiState.update {
                    feedUiState.value.copy(
                        isLoading = false,
                        feedItems = response.toUiState()
                    )
                }

            } catch (e: Exception) {
                Log.e("FeedException", "$e")
                when (e) {
                    is java.net.UnknownHostException -> {
                        _errorMessage.update {
                            e.message
                        }
                    }
                }
                _feedUiState.update {
                    feedUiState.value.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }

        }
    }

    companion object {
        const val NETFLIX_CHANNEL_ID = "UCiEEF51uRAeZeCo8CJFhGWw"
        const val WATCHA_CHANNEL_ID = "UCgmmc51A3qyAR3MvVX-rzCQ"
    }
}