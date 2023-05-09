package com.ponyo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard("JINA")
        }

        viewModel.fetchChannels()
        viewModel.fetchVideoItems()
//        setObservers()

    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
        MessageCard("Android")
    }

    @Composable
    fun MessageCard(name: String) {
        Text(text = "Hello $name!")
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