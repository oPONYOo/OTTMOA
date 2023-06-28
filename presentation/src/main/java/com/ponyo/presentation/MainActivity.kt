package com.ponyo.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.pullRefreshIndicatorTransform
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties
import com.ponyo.presentation.model.Channel
import com.ponyo.presentation.model.Feed
import com.ponyo.presentation.uistate.FeedUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val feedUiState by viewModel.feedUiState.collectAsState()
            val errorMessage by viewModel.errorMessage.collectAsState(null)

            var isRefreshing by remember { mutableStateOf(false) }
            var nowId by remember { mutableStateOf("") }
            val pullRefreshState = rememberPullRefreshState(
                refreshing = isRefreshing,
                onRefresh = {
                    Log.e("REFRESH", "REFRESH")
                    isRefreshing = true
                    if (nowId.isEmpty()) viewModel.getAllFeedItems()
                    else viewModel.fetchFeeds(nowId)
                })

            val pullRefreshModifier = Modifier.pullRefresh(pullRefreshState)

            Column {
                ChannelList(viewModel = viewModel, onClick = { id ->
                    nowId = id
                    if (id.isEmpty()) viewModel.getAllFeedItems()
                    else viewModel.fetchFeeds(id)

                })
                Box(modifier = pullRefreshModifier, contentAlignment = Alignment.TopCenter) {
                    FeedList(
                        modifier = Modifier,
                        feedUiState = feedUiState,
                        errorMessage = errorMessage
                    )
                    Indicator(refreshing = isRefreshing, state = pullRefreshState)
                }
            }

            LaunchedEffect(feedUiState.isRefreshing) {
                isRefreshing = false
                viewModel.initFeedUiState()
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
    val errorMessage by viewModel.errorMessage.collectAsState(null)
    errorMessage?.let {
        makeToast(LocalContext.current, it)
    }

    Row {
        ChannelItem(
            modifier = modifier,
            item = Channel(thumbnail = "", channelName = "ALL", channelId = "", recentDate = ""),
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
    feedUiState: FeedUiState,
    errorMessage: String?
) {
    errorMessage?.let {
        makeToast(LocalContext.current, it)
    }


    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = feedUiState.feedItems) {
        coroutineScope.launch {
            listState.animateScrollToItem(index = 0)
        }

    }


    LazyColumn(state = listState) {
        items(
            feedUiState.feedItems,
            key = { item -> item.date }) { item ->
            FeedItem(
                modifier = modifier,
                item = item
            )
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
                onClick?.let {
                    onClick(item.channelId)
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
fun FeedItem(
    modifier: Modifier,
    item: Feed
) {

    var bottomSheetDialogState by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .wrapContentSize()
            .clickable { bottomSheetDialogState = true }
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

    if (bottomSheetDialogState) FeedBottomSheet(
        modifier = modifier,
        item = item,
        onDismissRequest = { bottomSheetDialogState = it }
    )
}


@Composable
fun FeedBottomSheet(
    modifier: Modifier,
    item: Feed,
    onDismissRequest: (Boolean) -> Unit
) {
    BottomSheetDialog(
        onDismissRequest = {
            onDismissRequest(false)
        },
        properties = BottomSheetDialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = true,
            dismissWithAnimation = true,


            )

    ) {
        // content
        Surface(
            modifier = modifier.background(color = Color.White, shape = RectangleShape)
        ) {
            Column {
                Text(text = item.date)
                Text(text = "링크첨부")
                Text(text = "링크첨부")
                Text(text = "링크첨부")
                Text(text = "링크첨부")
                Text(text = "링크첨부")
                Text(text = "링크첨부")
            }

        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Indicator(
    modifier: Modifier = Modifier,
    state: PullRefreshState,
    refreshing: Boolean
) {
    val indicatorSize = 40.dp

    Surface(
        modifier = modifier
            .size(indicatorSize)
            .pullRefreshIndicatorTransform(state, true),
        shape = CircleShape,
        elevation = if (refreshing) 16.dp else 0.dp
    ) {
        if (refreshing) {
            val transition = rememberInfiniteTransition()
            val degree by transition.animateFloat(
                initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1000,
                        easing = LinearEasing
                    )
                )
            )
            Ball(modifier = Modifier.rotate(degree), indicatorSize)
        } else {
            Ball(modifier = Modifier.rotate(state.progress * 180), indicatorSize)
        }
    }
}

// TODO: 그라데이션 인디케이터 구헌하기
@Composable
private fun Ball(modifier: Modifier = Modifier, size: Dp) {
    Canvas(modifier = modifier) {
        val strokeWidth = (size / 10).toPx()
        drawCircle(color = Color.Black, style = Stroke(strokeWidth))
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
    val channel =
        Channel(thumbnail = channelImg, channelName = "NETFLIX", channelId = "", recentDate = "1")
    Column {
        ChannelItem(modifier = Modifier, item = channel)
        FeedItem(modifier = Modifier, item = feed)
    }
}