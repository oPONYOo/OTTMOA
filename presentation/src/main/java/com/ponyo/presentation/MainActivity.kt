package com.ponyo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ponyo.presentation.model.Channel
import com.ponyo.presentation.model.Feed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column {
                ChannelList(viewModel = viewModel)
                FeedList(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun ChannelList(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val channelUiState by viewModel.channelUiState.collectAsState()
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(
                start = 10.dp,
                end = 10.dp
            )
    ) {
        items(channelUiState.channelItems, key = { item -> item.recentDate }) { item ->
            ChannelItem(item = item)
        }
    }
}

@Composable
fun FeedList(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val feedUiState by viewModel.feedUiState.collectAsState()
    LazyColumn {
        items(feedUiState.feedItems, key = { item -> item.date }) { item ->
            FeedItem(item = item)
        }
    }
}

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
                contentDescription = "channel info",

                )
        }
    }
}

@Composable
fun FeedItem(item: Feed) {
    Box(
        modifier = Modifier
            .wrapContentSize()
    )
    {
        Surface(
            modifier = Modifier
        )
        {
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.thumbnail)
                        .crossfade(true)
                        .build(),
                    contentDescription = "content description",

                    )
                Spacer(modifier = Modifier.height(5.dp))
                item.description?.let { Text(text = it) }
            }

        }
    }
}

@Preview
@Composable
fun PreviewScreen() {
    val feedImg =
        "https://i.ytimg.com/vi/MvpahYjX8WE/hqdefault.jpg"
    val feed = Feed(thumbnail = feedImg, date = "00", description = "이 영상은 어쩌고", bookMarked = false)

    val channelImg =
        "https://yt3.ggpht.com/SXKyE4XgHJtX4qLS-9FKDuZt9EpBfeFPlGmNQdqsfxW2FDaKOjE53Mb20E43QuQfNDritLK1aw=s88-c-k-c0x00ffffff-no-rj"
    val channel = Channel(thumbnail = channelImg, channelName = "NETFLIX", recentDate = "1")
    Column {
        ChannelItem(item = channel)
        FeedItem(item = feed)
    }
}