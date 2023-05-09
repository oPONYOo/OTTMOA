package com.ponyo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ponyo.presentation.model.Channel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                ChannelList()
            }
//            MessageCard("JINA")
        }

        /*viewModel.fetchChannels()
        viewModel.fetchVideoItems()
        setObservers()*/

    }


    @Composable
    fun ChannelList() {
        val img =
            "https://yt3.ggpht.com/SXKyE4XgHJtX4qLS-9FKDuZt9EpBfeFPlGmNQdqsfxW2FDaKOjE53Mb20E43QuQfNDritLK1aw=s88-c-k-c0x00ffffff-no-rj"
        val channelList = listOf(
            Channel(thumbnail = img, channelName = "NETFLIX", recentDate = 1),
            Channel(thumbnail = img, channelName = "NETFLIXKOREA", recentDate = 2),
            Channel(thumbnail = img, channelName = "NETFLIXENG", recentDate = 3),
            Channel(thumbnail = img, channelName = "NETFLIXKOREA", recentDate = 4),
            Channel(thumbnail = img, channelName = "NETFLIXENG", recentDate = 5),
            Channel(thumbnail = img, channelName = "NETFLIXKOREA", recentDate = 6),
            Channel(thumbnail = img, channelName = "NETFLIXENG", recentDate = 7),
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp
                )
            ) {
            items(channelList, key = { item -> item.recentDate }) { item ->
                ChannelItem(item = item)
            }
        }


    }

    @Preview
    @Composable
    fun ChannelItem(item: Channel) {
        Box(
            modifier = Modifier
                .size(85.dp)
                .clip(CircleShape)
                .background(Color.Red)
        )
        {
            Surface(
                shape = CircleShape, modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center)
            )
            {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.thumbnail)
                        .crossfade(true)
                        .build(),
                    contentDescription = "ImageRequest example",

                    )
            }
        }


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