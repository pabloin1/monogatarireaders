package com.monogatari.app.profile.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.monogatari.app.core.data.local.shared_preferences.UserPreference
import com.monogatari.app.profile.data.repositories.CollectionRepository
import com.monogatari.app.profile.domain.models.CollectibleItemModel
import com.monogatari.app.profile.domain.models.LibraryState
import com.monogatari.app.profile.domain.use_cases.GetProfileInfoUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(app : Application): AndroidViewModel(app) {
    val username = UserPreference(app).getUserName() ?: "Unknown User"
    val state = mutableStateOf<LibraryState>(LibraryState.Idle)

    private val _collectionRepository = CollectionRepository()
    private val _getProfileInfoUseCase = GetProfileInfoUseCase()

    val userCollections : List<CollectibleItemModel> = _collectionRepository.getCollections()

    fun getInfo() {
        viewModelScope.launch {
            try {
                state.value = LibraryState.Loading
                val result = _getProfileInfoUseCase.execute()
                result.fold(
                    onSuccess = { res ->
                        state.value = LibraryState.Success(res)
                        Log.d("PROFILE_VM_TAG", "getInfo: $res")
                    },
                    onFailure = { error ->
                        state.value = LibraryState.Error(error.message.toString())
                        Log.d("PROFILE_VM_TAG", "getInfo: $error")
                    }
                )
            } catch (e: Exception) {
                state.value = LibraryState.Error(e.message.toString())
                Log.d("PROFILE_VM_TAG", "getInfo: $e")
            }
        }
    }
}