package com.monogatari.app.manga_chapter.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.monogatari.app.manga_chapter.domain.models.MangaChapterStatus
import com.monogatari.app.manga_chapter.domain.use_cases.GetMangaChapterInfoUseCase
import kotlinx.coroutines.launch

class MangaChapterViewmodel(app : Application) : AndroidViewModel(app) {
    private val _currentImageIndex = mutableIntStateOf(0)
    private val _userComment = mutableStateOf("")
    val state = mutableStateOf<MangaChapterStatus>(MangaChapterStatus.Idle)

    val currentImageIndex = _currentImageIndex
    val userComment = _userComment

    private val _getMangaChapterInfoUseCase = GetMangaChapterInfoUseCase()

    fun getMangaChapterInfo(chapterId : Int){
        viewModelScope.launch {
            try{
                state.value = MangaChapterStatus.Loading
                val mangaChapterInfo = _getMangaChapterInfoUseCase.execute(chapterId)
                mangaChapterInfo.fold(
                    onSuccess = {
                        state.value = MangaChapterStatus.Success(it)
                    },
                    onFailure = { error ->
                        state.value = MangaChapterStatus.Error("Error loading manga chapters: ${error.message}")
                    }
                )
            }catch (e: Exception){
                state.value = MangaChapterStatus.Error("Error loading manga chapters: ${e.message}")
            }
        }
    }

    fun onClickBefore() {
        if (_currentImageIndex.intValue > 0) {
            val newIndex = _currentImageIndex.intValue - 1
            _currentImageIndex.intValue = newIndex
        }
    }

    fun onClickAfter() {
        val imageList = (state.value as MangaChapterStatus.Success).chapters.pages
        if (_currentImageIndex.intValue < imageList.size - 1) {
            val newIndex = _currentImageIndex.intValue + 1
            _currentImageIndex.intValue = newIndex
        }
    }

    fun onUserCommentChange(newComment: String) {
        _userComment.value = newComment
    }

    fun onSubmitComment() {

    }
}