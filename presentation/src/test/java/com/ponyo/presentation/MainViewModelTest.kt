package com.ponyo.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.ponyo.domain.usecase.GetChannelUseCase
import com.ponyo.domain.usecase.GetFeedUseCase
import com.ponyo.presentation.uistate.ChannelUiState
import com.ponyo.presentation.util.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


// viewModel에서 검증해야할 것들
// 1. viewModel의 상태 변화
// 2. 외부 객체(Usecase)의 함수 호출 여부
// 3. uiState 상태 변화


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
internal class MainViewModelTest {


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutinesRule = MainDispatcherRule()

    private lateinit var viewModel: MainViewModel


    @MockK
    private lateinit var getChannelUseCase: GetChannelUseCase

    @MockK
    private lateinit var getFeedUseCase: GetFeedUseCase


    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = MainViewModel(getChannelUseCase, getFeedUseCase)
    }

    @Test
    fun getEmptyChannelList() = runTest {

        val expectUiState = ChannelUiState(
            isLoading = false,
            isError = false,
            channelItems = emptyList()
        )


        // when
        viewModel.fetchChannels(NETFLIX_CHANNEL_ID)


        // then
        val currentChannelUiState: ChannelUiState = viewModel.channelUiState.value
        Assert.assertEquals(currentChannelUiState, expectUiState)
    }


    companion object {
        const val NETFLIX_CHANNEL_ID = "UCiEEF51uRAeZeCo8CJFhGWw"
        const val WATCHA_CHANNEL_ID = "UCgmmc51A3qyAR3MvVX-rzCQ"
    }

}


