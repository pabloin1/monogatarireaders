package com.monogatari.app.manga_chapter.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateBefore
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monogatari.app.core.data.states.viewmodels.LocalViewModelProvider
import com.monogatari.app.core.ui.theme.BackgroundGrayItem
import com.monogatari.app.core.ui.theme.GlowingYellow
import com.monogatari.app.core.ui.theme.PrimaryBlack
import com.monogatari.app.manga_chapter.domain.models.MangaChapterStatus
import com.monogatari.app.manga_chapter.ui.composables.chapter_stats.ChapterStats
import com.monogatari.app.manga_chapter.ui.composables.comments_section.CommentsSection
import com.monogatari.app.manga_chapter.ui.composables.image_section.ImageSection
import com.monogatari.app.manga_chapter.ui.composables.navigation_buttons.NavigationButton
import com.monogatari.app.manga_chapter.ui.composables.top_bar_button.TopBarButton
import com.monogatari.app.router.data.NavigationData
import com.monogatari.app.router.data.states.LocalRouter
import com.monogatari.app.router.data.states.navigateTo
import com.monogatari.app.shared.ui.composables.CustomSnackBar

@Composable
fun MangaChapterScreen(
    mangaId: String,
    chapterId: String,
) {
    val viewModel = LocalViewModelProvider.current.mangaChapterViewmodel
    val currentImageIndex = viewModel.currentImageIndex.intValue
    val state = viewModel.state.value

    val route = LocalRouter.current

    LaunchedEffect(chapterId){
        viewModel.getMangaChapterInfo(chapterId.toInt())
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGrayItem)
    ) {
        when(state){
            is MangaChapterStatus.Loading -> { CircularProgressIndicator(
                color = GlowingYellow,
                modifier = Modifier
                    .align(Alignment.Center),
            ) }
            is MangaChapterStatus.Error -> { CustomSnackBar(
                text = state.message,
                action = {
                    Text(
                        text = "Go to Manga",
                        color = PrimaryBlack,
                        modifier = Modifier.clickable {
                            route.navigateTo(NavigationData.login)
                        }
                    )
                }
            ) }
            is MangaChapterStatus.Success -> {
                val imageList = state.chapters.pages
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Top bar with back button and title
                    TopBarButton(title = state.chapters.mangaTitle, subtitle = "Episode ${state.chapters.chapterNumber}", onBackClick = {
                        route.navigateTo(NavigationData.detailRoute(mangaId))
                    })

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        ChapterStats(viewsCount = state.chapters.viewCount.toInt() )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                        ) {
                            CommentsSection( chapterId = chapterId.toInt() )
                            // Main image with zoom functionality
                            ImageSection(
                                image = imageList[currentImageIndex],
                                currentImageIndex = currentImageIndex,
                            )
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                }

                // Navigation buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavigationButton(
                        onClick = {
                            viewModel.onClickBefore()
                        },
                        icon = Icons.AutoMirrored.Filled.NavigateBefore,
                        contentDescription = "Back",
                        isEnabled = currentImageIndex > 0
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    NavigationButton(
                        onClick = {
                            viewModel.onClickAfter()
                        },
                        icon = Icons.AutoMirrored.Filled.NavigateNext,
                        contentDescription = "Next",
                        isEnabled = currentImageIndex < imageList.size - 1
                    )
                }
            }
            else -> {}
        }
    }
}