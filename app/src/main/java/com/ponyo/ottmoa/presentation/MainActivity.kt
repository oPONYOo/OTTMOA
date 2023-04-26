package com.ponyo.ottmoa.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ponyo.ottmoa.R
import com.ponyo.ottmoa.provider.MainViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelProvider()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.fetchChannels()
        }

        setObservers()

    }

    private fun setObservers() {
        viewModel.channelInfo.observe(this) {
            println(it)
        }
    }
}