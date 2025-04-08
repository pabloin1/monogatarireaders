package com.monogatari.app.manga_chapter.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.monogatari.app.R

class MangaChapterViewmodel(app : Application) : AndroidViewModel(app) {
    private val _currentImageIndex = mutableIntStateOf(0)
    private val _userComment = mutableStateOf("")
    private val _imageList = mutableStateOf(
        listOf(
            R.drawable.satoru_gojo,
            R.drawable.tokyo_ghoul_presentation,
            R.drawable.monogatari_logo
        )
    )

    val currentImageIndex= _currentImageIndex
    val userComment = _userComment
    val images = _imageList

    fun onClickBefore() {
        if (_currentImageIndex.intValue > 0) {
            val newIndex = _currentImageIndex.intValue - 1
            _currentImageIndex.intValue = newIndex
        }
    }

    fun onClickAfter() {
        if (_currentImageIndex.intValue < _imageList.value.size - 1) {
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