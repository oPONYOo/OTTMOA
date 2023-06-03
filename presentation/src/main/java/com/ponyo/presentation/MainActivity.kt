package com.ponyo.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ponyo.presentation.model.Channel
import com.ponyo.presentation.model.Feed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var channelText by rememberSaveable { mutableStateOf("ALL") }
            Column {
                ChannelList(viewModel = viewModel, onClick = { channelText = it })
                FeedList(viewModel = viewModel, channelTitle = channelText)
            }
        }
    }
}

@Composable
fun ChannelList(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    onClick: ((String) -> Unit)? = null
) {
    val channelUiState by viewModel.channelUiState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    errorMessage?.let {
        makeToast(LocalContext.current, it)
    }

    Row {
        ChannelItem(
            modifier = modifier,
            item = Channel(thumbnail = "", channelName = "ALL", recentDate = ""),
            onClick = onClick
        )
        Spacer(modifier = modifier.width(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            items(channelUiState.channelItems, key = { item -> item.recentDate }) { item ->
                ChannelItem(
                    modifier = modifier,
                    item = item, onClick = onClick
                )
            }
        }
    }


}

@Composable
fun FeedList(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    channelTitle: String
) {
    val feedUiState by viewModel.feedUiState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    errorMessage?.let {
        makeToast(LocalContext.current, it)
    }

    val feedItems =
        if (channelTitle == "ALL") feedUiState.feedItems else feedUiState.feedItems.filter { it.channelTitle == channelTitle }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = feedItems) {
        coroutineScope.launch {
            listState.animateScrollToItem(index = 0)
        }
    }


    LazyColumn(state = listState) {
        items(
            feedItems,
            key = { item -> item.date }) { item ->
            FeedItem(modifier = modifier, item = item)
        }
    }
}

private fun makeToast(context: Context, errorMessage: String) =
    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()

@Composable
fun ChannelItem(modifier: Modifier, item: Channel, onClick: ((String) -> Unit)? = null) {
    Box(
        modifier = modifier
            .size(85.dp)
            .clip(CircleShape)
            .background(Color.Red)
            .clickable {
                if (onClick != null) {
                    onClick(item.channelName)
                }
            }
    )
    {
        Surface(
            shape = CircleShape, modifier = modifier
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

            if (item.channelName == "ALL") {
                Text(
                    text = item.channelName,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(510),
                    fontSize = 30.sp
                )
            }
        }
    }
}

@Composable
fun FeedItem(modifier: Modifier, item: Feed) {
    Box(
        modifier = modifier
            .wrapContentSize()
    )
    {
        Surface()
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
    val feed = Feed(
        thumbnail = feedImg,
        date = "00",
        description = "이 영상은 어쩌고",
        bookMarked = false,
        channelTitle = ""
    )

    val channelImg =
        "https://yt3.ggpht.com/SXKyE4XgHJtX4qLS-9FKDuZt9EpBfeFPlGmNQdqsfxW2FDaKOjE53Mb20E43QuQfNDritLK1aw=s88-c-k-c0x00ffffff-no-rj"
    val channel = Channel(thumbnail = channelImg, channelName = "NETFLIX", recentDate = "1")
    Column {
        ChannelItem(modifier = Modifier, item = channel)
        FeedItem(modifier = Modifier, item = feed)
    }
}