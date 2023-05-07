package com.ponyo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

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