package com.monogatari.app.manga_chapter.ui.composables.comments_section.comments_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.monogatari.app.R
import com.monogatari.app.core.ui.theme.BackgroundGrayItem
import com.monogatari.app.core.ui.theme.BorderGray
import com.monogatari.app.core.ui.theme.PrimaryWhite
import com.monogatari.app.core.utils.extensions.toFormattedDate

@Composable
fun CommentItem(
    modifier: Modifier = Modifier,
    userImg : String?,
    userName : String,
    comment : String,
    createdAt : String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        AsyncImage(
            model = userImg,
            contentDescription = "Profile Avatar",
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
                .border(2.dp, BorderGray, CircleShape)
                .background(Color.Black),
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.satoru_gojo)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
                .background(
                    BackgroundGrayItem.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(10.dp)
                )
                .border(2.dp, BorderGray, RoundedCornerShape(10.dp))
                .padding(10.dp),
        ){
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = userName,
                    color = PrimaryWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier.weight(1f).padding(start = 5.dp),
                    text = createdAt.toFormattedDate(),
                    color = Color.Gray,
                    fontSize = 12.sp,
                )
                Row(
                    modifier = Modifier
                        .width(50.dp)
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(18.dp)
                    )
                    Icon(
                        imageVector = Icons.Filled.ThumbDown,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(18.dp)
                    )
                }
            }
            Text(
                text = comment,
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}