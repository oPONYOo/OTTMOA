package com.ponyo.data.di

import android.app.Application
import com.ponyo.data.db.InfoDao
import com.ponyo.data.db.InfoDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class DAOService

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Singleton
    @Provides
    fun getAppDB(context: Application): InfoDatabase{
        return InfoDatabase.getAppDB(context)
    }

    @DAOService
    @Singleton
    @Provides
    fun getDao(appDB: InfoDatabase): InfoDao {
        return appDB.getDao()
    }

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher
