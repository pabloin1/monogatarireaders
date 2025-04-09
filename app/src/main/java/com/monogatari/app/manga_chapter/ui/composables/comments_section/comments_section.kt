package com.monogatari.app.manga_chapter.ui.composables.comments_section

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monogatari.app.core.data.states.viewmodels.LocalViewModelProvider
import com.monogatari.app.core.ui.theme.BackgroundBlue
import com.monogatari.app.core.ui.theme.BorderGray
import com.monogatari.app.core.ui.theme.GlowingYellow
import com.monogatari.app.manga_chapter.domain.models.CommentChapterStatus
import com.monogatari.app.manga_chapter.ui.composables.comments_section.comments_list.CommentsList
import com.monogatari.app.manga_chapter.ui.composables.comments_section.comments_top_bar.CommentsTopBar
import com.monogatari.app.manga_chapter.ui.composables.comments_section.user_comment.UserComment
import com.monogatari.app.shared.ui.composables.CustomSnackBar

@Composable
fun CommentsSection(chapterId : Int) {
    val viewModel = LocalViewModelProvider.current.mangaChapterViewmodel
    val commentState = viewModel.commentStatus.value

    LaunchedEffect(viewModel.isCommentSubmit.value){
        viewModel.getChapterComments(chapterId)
        viewModel.isCommentSubmit.value = false
    }

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

        when(commentState){
            is CommentChapterStatus.Loading -> {
                CircularProgressIndicator(
                    color = GlowingYellow,
                    modifier = Modifier
                        .padding(top = 15.dp),
                )
            }

            is CommentChapterStatus.Error -> CustomSnackBar(text = commentState.message)
            is CommentChapterStatus.Success -> {
                CommentsTopBar(commentsCount = commentState.comments.size)

                // User input comment
                UserComment(
                    value = viewModel.userComment.value,
                    onValueChange = { viewModel.onUserCommentChange(it) },
                    onSubmitComment = { viewModel.onSubmitComment() },
                )
                CommentsList(
                    comments = commentState.comments
                )
            }
            else -> {}
        }
    }
}
