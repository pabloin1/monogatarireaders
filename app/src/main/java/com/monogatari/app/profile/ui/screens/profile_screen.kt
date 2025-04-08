package com.monogatari.app.profile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monogatari.app.core.data.states.viewmodels.LocalViewModelProvider
import com.monogatari.app.core.ui.theme.PrimaryBlack
import com.monogatari.app.profile.ui.composables.collection_section.CollectionContainer
import com.monogatari.app.profile.ui.composables.header_section.ProfileHeader
import com.monogatari.app.profile.ui.composables.library_section.LibrarySection
import com.monogatari.app.shared.ui.layouts.AppLayout

@Composable
fun ProfileScreen() {
    val profileViewModel = LocalViewModelProvider.current.profileViewModel
   AppLayout {
       Column(
           modifier = Modifier
               .fillMaxSize()
               .background(PrimaryBlack)
               .verticalScroll(rememberScrollState())
       ) {
           ProfileHeader()
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
                   library = profileViewModel.userLibrary,
               )
           }

       }
   }
}