package com.ponyo.data.di

import com.ponyo.domain.YoutubeRepository
import com.ponyo.domain.usecase.GetChannelUseCase
import com.ponyo.domain.usecase.GetFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideGetChannelUseCase(repository: YoutubeRepository): GetChannelUseCase =
        GetChannelUseCase(repository)

    @Provides
    fun provideGetFeedUseCase(repository: YoutubeRepository): GetFeedUseCase =
        GetFeedUseCase(repository)

}