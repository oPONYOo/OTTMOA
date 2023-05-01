package com.ponyo.ottmoa.provider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ponyo.ottmoa.data.YoutubeRepository
import com.ponyo.ottmoa.data.YoutubeRepositoryImpl
import com.ponyo.ottmoa.presentation.MainViewModel

class MainViewModelProvider: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            youtubeRepository = provideRepository()
        ) as T
    }
}