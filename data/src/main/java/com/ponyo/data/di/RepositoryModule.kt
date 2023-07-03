package com.ponyo.data.di

import com.ponyo.data.LocalInfoRepositoryImpl
import com.ponyo.data.YoutubeApi
import com.ponyo.data.YoutubeRepositoryImpl
import com.ponyo.data.db.InfoDao
import com.ponyo.domain.LocalInfoRepository
import com.ponyo.domain.YoutubeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideYoutubeRepository(
        @ApiService youtubeApi: YoutubeApi
    ): YoutubeRepository =
        YoutubeRepositoryImpl(youtubeApi)

    @Provides
    @Singleton
    fun provideLocalInfoRepository(
        @DAOService infoDao: InfoDao
    ): LocalInfoRepository = LocalInfoRepositoryImpl(infoDao)
}

