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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getChannelUseCase: GetChannelUseCase,
    private val getFeedUseCase: GetFeedUseCase
) : ViewModel() {

    init {
        fetchChannels(NETFLIX_CHANNEL_ID, WATCHA_CHANNEL_ID)
        fetchFeeds(NETFLIX_CHANNEL_ID, WATCHA_CHANNEL_ID)
    }

    private val _channelUiState = MutableStateFlow(ChannelUiState.Uninitialized)
    val channelUiState: StateFlow<ChannelUiState> = _channelUiState.asStateFlow()

    private val _feedUiState = MutableStateFlow(FeedUiState.Uninitialized)
    val feedUiState: StateFlow<FeedUiState> = _feedUiState.asStateFlow()



    // sharedFlow
    private val _errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()


     fun fetchChannels(vararg channelIdList: String) {

        val handler = CoroutineExceptionHandler { _, t ->

            Log.e("ChannelException", "$t")
            when (t) {
                is UnknownHostException -> _errorMessage.update {
                    t.message
                }
                // error 메세지 세분화
            }
            _channelUiState.update {
                channelUiState.value.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }

        viewModelScope.launch(handler) {
            val response = getChannelUseCase(*channelIdList)

            _channelUiState.update {
                channelUiState.value.copy(
                    isLoading = false,
                    channelItems = response.toUiState()
                )
            }
        }

    }

     fun fetchFeeds(vararg channelIdList: String) {
        viewModelScope.launch {
            try {
                val response = getFeedUseCase(*channelIdList)
                _feedUiState.update {
                    feedUiState.value.copy(
                        isLoading = false,
                        feedItems = response.toUiState()
                    )
                }

            } catch (e: Exception) {
                Log.e("FeedException", "$e")
                when (e) {
                    is UnknownHostException -> {
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