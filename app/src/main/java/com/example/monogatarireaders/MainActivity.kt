package com.example.monogatarireaders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.monogatarireaders.manga_chapter.ui.viewmodel.MangaChapterViewmodel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val mangaChapterViewmodel = ViewModelProvider(this)[MangaChapterViewmodel::class.java]
        setContent {
            App(
                mangaChapterViewmodel
            )
        }
    }
}