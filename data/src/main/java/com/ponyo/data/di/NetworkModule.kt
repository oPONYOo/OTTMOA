package com.ponyo.data.di


import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.ponyo.data.YoutubeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class ApiService

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {



    fun initFlipper(@ApplicationContext appContext: Context) {
        SoLoader.init(appContext, false)

        val client = AndroidFlipperClient.getInstance(appContext)
        client.addPlugin(InspectorFlipperPlugin(appContext, DescriptorMapping.withDefaults()))
        client.addPlugin(networkFlipperPlugin)
        client.start()


    }


    private val networkFlipperPlugin = NetworkFlipperPlugin()
    private val flipperOkHttpInterceptor = FlipperOkhttpInterceptor(networkFlipperPlugin)

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(flipperOkHttpInterceptor)
            .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/youtube/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


    @ApiService
    @Provides
    @Singleton
    fun provideApi(
        retrofit: Retrofit
    ): YoutubeApi = retrofit
        .create(YoutubeApi::class.java)
}