package com.monogatari.app.manga_chapter.ui.composables.comments_section.comments_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monogatari.app.R
import com.monogatari.app.manga_chapter.domain.adapters.CommentAdapter

@Composable
fun CommentsList(
    modifier: Modifier = Modifier,
    comments : List<CommentAdapter>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),

    ){
        for (comment in comments){
            CommentItem(
                userName = comment.user.displayName,
                userImg = comment.user?.profileImageUrl,
                comment = comment.content,
                createdAt = comment.createdAt,
            )
        }
    }
}