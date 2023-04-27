package com.ponyo.ottmoa.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.ponyo.ottmoa.R
import com.ponyo.ottmoa.provider.MainViewModelProvider


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelProvider()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchChannels()
        viewModel.fetchVideoItems()
        setObservers()

    }

    private fun setObservers() {
        viewModel.channelInfo.observe(this) {
            Log.e("CHANNEL", "$it")
        }

        viewModel.videoItems.observe(this) {
            Log.e("ITEMS", "$it ")
        }
    }
}