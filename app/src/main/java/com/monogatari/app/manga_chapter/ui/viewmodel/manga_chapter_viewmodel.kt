package com.monogatari.app.manga_chapter.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.monogatari.app.core.data.local.network.NetworkManager
import com.monogatari.app.manga_chapter.data.services.CreateCommentChapterService
import com.monogatari.app.manga_chapter.data.services.GetChapterCommentsService
import com.monogatari.app.manga_chapter.domain.dtos.CreateCommentDTO
import com.monogatari.app.manga_chapter.domain.models.CommentChapterStatus
import com.monogatari.app.manga_chapter.domain.models.MangaChapterStatus
import com.monogatari.app.manga_chapter.domain.use_cases.GetMangaChapterInfoUseCase
import com.monogatari.app.manga_chapter.domain.use_cases.GetMangaChapterLocalInfoUseCase
import kotlinx.coroutines.launch

class MangaChapterViewmodel(app : Application) : AndroidViewModel(app) {
    private val _currentImageIndex = mutableIntStateOf(0)
    private val _userComment = mutableStateOf("")
    val state = mutableStateOf<MangaChapterStatus>(MangaChapterStatus.Idle)
    val commentStatus = mutableStateOf<CommentChapterStatus>(CommentChapterStatus.Idle)
    val isCommentSubmit = mutableStateOf(false)

    val currentImageIndex = _currentImageIndex
    val userComment = _userComment

    private val _getMangaChapterInfoUseCase = GetMangaChapterInfoUseCase()
    private val _getMangaChapterLocalInfoUseCase = GetMangaChapterLocalInfoUseCase(app)
    private val _getChapterCommentsService = GetChapterCommentsService()
    private val _createCommentChapterService = CreateCommentChapterService()

    fun getMangaChapterInfo(chapterId : Int){
        viewModelScope.launch {
            if(NetworkManager.isOnline()){
                getNetworkInfo(chapterId)
            }else{
                getLocalInfo(chapterId)
            }
        }
    }

    private suspend fun getNetworkInfo(chapterId: Int){
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

    private suspend fun getLocalInfo(chapterId: Int){
        viewModelScope.launch {
            try {
                state.value = MangaChapterStatus.Loading
                val localInfo = _getMangaChapterLocalInfoUseCase.execute(chapterId)
                state.value = MangaChapterStatus.Success(localInfo)
                Log.d("MANGA_CHAPTER_VM", "getLocalInfo: $localInfo")
            }catch (e: Exception){
                state.value = MangaChapterStatus.Error("Error loading local info: ${e.message}")
            }
        }
    }

    fun getChapterComments(chapterId : Int){
        viewModelScope.launch {
            try{
                commentStatus.value = CommentChapterStatus.Loading
                val chapterComments = _getChapterCommentsService.getChapterComments(chapterId)
                chapterComments.fold(
                    onSuccess = {
                        commentStatus.value = CommentChapterStatus.Success(it)
                    },
                    onFailure = { error ->
                        commentStatus.value = CommentChapterStatus.Error("Error loading comments : ${error.message}")
                    }
                )
            }catch (e: Exception){
                commentStatus.value = CommentChapterStatus.Error("Error loading comments : ${e.message}")
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
        viewModelScope.launch {
            try {
                val chapterId = (state.value as MangaChapterStatus.Success).chapters.id
                val createCommentDTO = CreateCommentDTO(
                    chapterId = chapterId,
                    content = _userComment.value
                )
                val result = _createCommentChapterService.createComment(createCommentDTO)
                result.fold(
                    onSuccess = {
                        isCommentSubmit.value = true
                        onUserCommentChange("")
                    },
                    onFailure = { error ->
                        isCommentSubmit.value = false
                        commentStatus.value = CommentChapterStatus.Error("Error submitting comment: ${error.message}")
                    }
                )
            } catch (e: Exception) {
                isCommentSubmit.value = false
                commentStatus.value = CommentChapterStatus.Error("Error submitting comment: ${e.message}")
            }
        }
    }
}