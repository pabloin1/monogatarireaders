package com.monogatari.app.manga_chapter.ui.composables.comments_section.comments_list

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monogatari.app.core.ui.theme.BackgroundGrayItem
import com.monogatari.app.core.ui.theme.BorderGray
import com.monogatari.app.core.ui.theme.PrimaryWhite

@Composable
fun CommentItem(
    modifier: Modifier = Modifier,
    user_img : Int,
    user_name : String,
    user_comment : String,
    time_ago : String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Image(
            painter = painterResource(id = user_img),
            contentDescription = "Profile Avatar",
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
                .border(2.dp, BorderGray, CircleShape)
                .background(Color.Black),
            contentScale = ContentScale.Crop
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
            Row {
                Text(
                    modifier = Modifier.weight(1f),
                    text = user_name,
                    color = PrimaryWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = "$time_ago ago",
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
                text = user_comment,
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}