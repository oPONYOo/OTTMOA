package com.ponyo.data.di

import com.ponyo.domain.LocalInfoRepository
import com.ponyo.domain.YoutubeRepository
import com.ponyo.domain.usecase.GetChannelUseCase
import com.ponyo.domain.usecase.GetFeedUseCase
import com.ponyo.domain.usecase.GetLocalInfoUseCase
import com.ponyo.domain.usecase.InsertLocalInfoUseCase
import com.ponyo.domain.usecase.UpdateLocalInfoUseCase
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

    @Provides
    fun provideGetLocalInfoList(repository: LocalInfoRepository): GetLocalInfoUseCase =
        GetLocalInfoUseCase(repository)

    @Provides
    fun provideInsertLocalInfo(repository: LocalInfoRepository): InsertLocalInfoUseCase =
        InsertLocalInfoUseCase(repository)

    @Provides
    fun provideUpdateLocalInfo(repository: LocalInfoRepository): UpdateLocalInfoUseCase =
        UpdateLocalInfoUseCase(repository)

}