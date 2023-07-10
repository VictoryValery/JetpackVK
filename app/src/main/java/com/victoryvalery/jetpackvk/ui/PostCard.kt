package com.victoryvalery.jetpackvk.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victoryvalery.jetpackvk.R
import com.victoryvalery.jetpackvk.ui.theme.JetpackVKTheme

@Preview
@Composable
fun PreviewLight() {
    JetpackVKTheme(false) {
        PostCard(false)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF4FA29E)
@Composable
fun PreviewDark() {
    JetpackVKTheme(true) {
        PostCard(true)
    }
}

@Composable
fun PostCard(isSystemInDarkTheme: Boolean) {
    val backColor = if (isSystemInDarkTheme) Color.DarkGray else Color.White
    Card(
        modifier = Modifier
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
            PostHeader()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.template_card_text),
                style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.SansSerif)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.fresh_start),
                contentDescription = "post",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            PostBottomStatistics()
        }
    }
}

@Composable
private fun PostBottomStatistics() {
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
            CountAndIcon("206", R.drawable.ic_views_count)
        }
        CountAndIcon("206", R.drawable.ic_share)
        CountAndIcon("206", R.drawable.ic_comment)
        CountAndIcon("206", R.drawable.ic_like)
    }
}

@Composable
private fun PostHeader() {
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
            painter = painterResource(id = R.drawable.base_avatar),
            contentDescription = "Avatar"
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "User name",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = "05:42",
                style = TextStyle(fontSize = 12.sp, color = Color.Gray)
            )
        }
        Icon(Icons.Rounded.MoreVert, contentDescription = "Menu")
    }
}

@Composable
fun CountAndIcon(count: String, iconId: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically
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