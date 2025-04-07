package com.example.monogatarireaders.manga_chapter.ui.composables.comments_section

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.monogatarireaders.core.ui.theme.BackgroundBlue
import com.example.monogatarireaders.core.ui.theme.BorderGray
import com.example.monogatarireaders.manga_chapter.ui.composables.comments_section.comments_list.CommentsList
import com.example.monogatarireaders.manga_chapter.ui.composables.comments_section.comments_top_bar.CommentsTopBar
import com.example.monogatarireaders.manga_chapter.ui.composables.comments_section.user_comment.UserComment

@Composable
fun CommentsSection(
    modifier: Modifier = Modifier,
    onSubmitComment : () -> Unit,
    commentValue : String,
    onCommentValueChange : (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 520.dp)
            .border(
                width = 2.dp,
                color = BorderGray,
                shape = MaterialTheme.shapes.large
            )
            .background(
                BackgroundBlue,
                shape = MaterialTheme.shapes.large
            )
            .padding(horizontal = 16.dp, vertical = 18.dp),

        ) {
        CommentsTopBar(commentsCount = 100)

        // User input comment
        UserComment(
            value = commentValue,
            onValueChange = onCommentValueChange,
            onSubmitComment = onSubmitComment,
        )

        CommentsList()
    }
}
