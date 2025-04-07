package com.example.monogatarireaders.core.data.providers.viewmodel

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.monogatarireaders.discover_manga.ui.viewmodels.DiscoverMangaViewModel
import com.example.monogatarireaders.login.ui.viewmodel.LoginViewModel
import com.example.monogatarireaders.manga_chapter.ui.viewmodel.MangaChapterViewmodel
import com.example.monogatarireaders.manga_detail.ui.viewmodels.MangaDetailViewModel
import com.example.monogatarireaders.profile.ui.viewmodels.ProfileViewModel
import com.example.monogatarireaders.register.ui.viewmodel.RegisterViewModel

class ViewModelManager(private val context: Context) {

    private val viewModelCache = mutableMapOf<Class<out AndroidViewModel>, AndroidViewModel>()

    private fun <T : AndroidViewModel> getViewModel(viewModelClass: Class<T>): T {

        val cachedViewModel = viewModelCache[viewModelClass]

        cachedViewModel?.let {
            @Suppress("UNCHECKED_CAST")
            return it as T
        }

        val viewModelStoreOwner = context as? ViewModelStoreOwner
            ?: throw IllegalArgumentException("Context must be a ViewModelStoreOwner")

        val viewModel = ViewModelProvider(viewModelStoreOwner)[viewModelClass]

        viewModelCache[viewModelClass] = viewModel

        return viewModel
    }

    val loginViewmodel : LoginViewModel by lazy {
        getViewModel(LoginViewModel::class.java)
    }

    val registerViewModel : RegisterViewModel by lazy {
        getViewModel(RegisterViewModel::class.java)
    }

    val mangaChapterViewmodel : MangaChapterViewmodel by lazy {
        getViewModel(MangaChapterViewmodel::class.java)
    }

    val discoverMangaViewModel : DiscoverMangaViewModel by lazy {
        getViewModel(DiscoverMangaViewModel::class.java)
    }

    val mangaDetailViewModel : MangaDetailViewModel by lazy {
        getViewModel(MangaDetailViewModel::class.java)
    }
    val profileViewModel : ProfileViewModel by lazy {
        getViewModel(ProfileViewModel::class.java)
    }
}
