package com.example.monogatarireaders.manga_chapter.ui.composables.comments_section.user_comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monogatarireaders.R
import com.example.monogatarireaders.core.ui.theme.BorderGray

@Composable
fun UserComment(
    value : String,
    onValueChange : (String) -> Unit,
    onSubmitComment : () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Image(
            painter = painterResource(id = R.drawable.satoru_gojo),
            contentDescription = "Profile Avatar",
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
                .border(2.dp, BorderGray, CircleShape)
                .background(Color.Black),
            contentScale = ContentScale.Crop
        )
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    "Write a comment...",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
                .defaultMinSize(minHeight = 55.dp)
                .border(2.dp, BorderGray, CircleShape),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1A222D),
                unfocusedContainerColor = Color(0xFF1A222D),
                disabledContainerColor = Color(0xFF1A222D),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(50.dp),
            trailingIcon = {
                IconButton(
                    onClick = onSubmitComment
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send",
                        tint = Color.White,
                        modifier = Modifier.rotate(-90f),
                    )
                }
            },

            )
    }
}