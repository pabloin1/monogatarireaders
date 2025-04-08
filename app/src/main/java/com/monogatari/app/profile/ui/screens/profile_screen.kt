package com.monogatari.app.profile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monogatari.app.core.data.states.viewmodels.LocalViewModelProvider
import com.monogatari.app.core.ui.theme.GlowingYellow
import com.monogatari.app.core.ui.theme.PrimaryBlack
import com.monogatari.app.profile.domain.models.LibraryState
import com.monogatari.app.profile.ui.composables.collection_section.CollectionContainer
import com.monogatari.app.profile.ui.composables.header_section.ProfileHeader
import com.monogatari.app.profile.ui.composables.library_section.LibrarySection
import com.monogatari.app.shared.ui.composables.CustomSnackBar
import com.monogatari.app.shared.ui.layouts.AppLayout

@Composable
fun ProfileScreen() {
    val profileViewModel = LocalViewModelProvider.current.profileViewModel
    val state = profileViewModel.state.value
   AppLayout {
       Column(
           modifier = Modifier
               .fillMaxSize()
               .background(PrimaryBlack)
               .verticalScroll(rememberScrollState()),
       ) {
           when(state){
                is LibraryState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(top = 360.dp)
                            .align(Alignment.CenterHorizontally),
                        color = GlowingYellow
                    )
                }
                is LibraryState.Success -> {
                    ProfileHeader(
                        username = profileViewModel.username,
                        chaptersRead = state.library.chaptersRead,
                        favorites = state.library.libraryCount,
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        CollectionContainer(
                            collectibles = profileViewModel.userCollections,
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        LibrarySection(
                            library = state.library.library,
                        )
                    }
                }
                is LibraryState.Error -> { CustomSnackBar(text = state.message) }
                else -> {}
           }
       }
   }
}