package com.victoryvalery.jetpackvk.ui.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.victoryvalery.jetpackvk.domain.FeedPostItem
import com.victoryvalery.jetpackvk.domain.PostComment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    onBackPressed: () -> Unit,
    feedPost: FeedPostItem
) {
    val viewModel: CommentsViewModel = viewModel(
        factory = CommentsViewModelFactory(feedPost)
    )
    val screenState = viewModel.commentsState.collectAsState()
    val currentState = screenState.value
    if (currentState !is CommentsScreenState.Comments)
        return

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Comments for FeedPost Id: ${currentState.feedPost.publicationId}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = paddingValues),
            contentPadding = PaddingValues(bottom = 80.dp, end = 8.dp, start = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(items = currentState.comments, key = { pc: PostComment -> pc.id }) {
                    SingleComment(singleComment = it)
                }
            }
        )
    }

}

@Composable
fun SingleComment(
    modifier: Modifier = Modifier,
    singleComment: PostComment
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = singleComment.authorAvatarId),
            contentDescription = "Avatar"
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = singleComment.authorName,
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = "${singleComment.id}: ${singleComment.commentText}",
                style = TextStyle(fontSize = 14.sp)
            )
            Text(
                text = singleComment.publicationDate,
                style = TextStyle(fontSize = 12.sp, color = Color.Gray)
            )
        }
    }
}

@Composable
@Preview
fun PreviewSingleComment() {
    SingleComment(
        singleComment = PostComment(0)
    )
}