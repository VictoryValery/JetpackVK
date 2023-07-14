package com.victoryvalery.jetpackvk.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victoryvalery.jetpackvk.R
import com.victoryvalery.jetpackvk.domain.FeedPostItem
import com.victoryvalery.jetpackvk.domain.StatisticsItem
import com.victoryvalery.jetpackvk.domain.StatisticsType
import com.victoryvalery.jetpackvk.domain.StatisticsType.COMMENTS
import com.victoryvalery.jetpackvk.domain.StatisticsType.LIKES
import com.victoryvalery.jetpackvk.domain.StatisticsType.SHARES
import com.victoryvalery.jetpackvk.domain.StatisticsType.VIEWS

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPostItem: FeedPostItem,
    onLikeItemClickListener: (StatisticsItem) -> Unit,
    onShareItemClickListener: (StatisticsItem) -> Unit,
    onCommentItemClickListener: (StatisticsItem) -> Unit,
    onViewsItemClickListener: (StatisticsItem) -> Unit,
) {
    val backColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = backColor,
            contentColor = MaterialTheme.colorScheme.onBackground,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            PostHeader(feedPostItem)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feedPostItem.publicationText,
                style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.SansSerif)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = feedPostItem.publicationImageId),
                contentDescription = "post",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.FillWidth
            )
            PostBottomStatistics(
                statistics = feedPostItem.publicationStatistics,
                onLikeItemClickListener = onLikeItemClickListener,
                onShareItemClickListener = onShareItemClickListener,
                onCommentItemClickListener = onCommentItemClickListener,
                onViewsItemClickListener = onViewsItemClickListener
            )
        }
    }
}

@Composable
private fun PostBottomStatistics(
    statistics: List<StatisticsItem>,
    onLikeItemClickListener: (StatisticsItem) -> Unit,
    onShareItemClickListener: (StatisticsItem) -> Unit,
    onCommentItemClickListener: (StatisticsItem) -> Unit,
    onViewsItemClickListener: (StatisticsItem) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
        ) {
            val viewsItem = statistics.getItemByType(VIEWS)
            CountAndIcon(
                count = viewsItem.count.toString(),
                iconId = R.drawable.ic_views_count,
                onItemClickListener = { onViewsItemClickListener(viewsItem) }
            )
        }
        val sharesItem = statistics.getItemByType(SHARES)
        val commentsItem = statistics.getItemByType(COMMENTS)
        val likesItem = statistics.getItemByType(LIKES)
        CountAndIcon(
            count = sharesItem.count.toString(),
            iconId = R.drawable.ic_share,
            onItemClickListener = { onShareItemClickListener(sharesItem) }
        )
        CountAndIcon(
            count = commentsItem.count.toString(),
            iconId = R.drawable.ic_comment,
            onItemClickListener = { onCommentItemClickListener(commentsItem) }
        )
        CountAndIcon(
            count = likesItem.count.toString(),
            iconId = R.drawable.ic_like,
            onItemClickListener = { onLikeItemClickListener(likesItem) }
        )
    }
}

private fun List<StatisticsItem>.getItemByType(type: StatisticsType): StatisticsItem {
    return find { it.type == type }
        ?: throw IllegalStateException("There is no such StatisticsType $type at StatisticsItem List")
}

@Composable
private fun PostHeader(feedPostItem: FeedPostItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = feedPostItem.authorAvatarId),
            contentDescription = "Avatar"
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = feedPostItem.authorName,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = feedPostItem.publicationDate,
                style = TextStyle(fontSize = 12.sp, color = Color.Gray)
            )
        }
        Icon(Icons.Rounded.MoreVert, contentDescription = "Menu")
    }
}

@Composable
fun CountAndIcon(
    count: String,
    iconId: Int,
    onItemClickListener: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onItemClickListener()
        }
    ) {
        Image(painter = painterResource(id = iconId), contentDescription = "icon")
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = count,
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.width(16.dp))
    }
}


//@Preview
//@Composable
//fun PreviewLight() {
//    JetpackVKTheme(false) {
//        PostCard()
//    }
//}
//
//@Preview(showBackground = true, backgroundColor = 0xFF4FA29E)
//@Composable
//fun PreviewDark() {
//    JetpackVKTheme(true) {
//        PostCard()
//    }
//}