package com.example.monogatarireaders.manga_chapter.ui.composables.comments_section.comments_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.monogatarireaders.R

@Composable
fun CommentsList(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),

    ){
        CommentItem(
            user_img = R.drawable.satoru_gojo,
            user_name = "Satoru Gojo",
            user_comment = "I love this manga!",
            time_ago = "3d"
        )
        CommentItem(
            user_img = R.drawable.tokyo_ghoul_presentation,
            user_name = "Ken Kaneki",
            user_comment = "This chapter was amazing! I can't wait for the next one.",
            time_ago = "2h"
        )
    }
}