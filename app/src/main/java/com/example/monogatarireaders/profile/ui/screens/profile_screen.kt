package com.example.monogatarireaders.profile.ui.screens

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
import com.example.monogatarireaders.core.data.states.viewmodels.LocalViewModelProvider
import com.example.monogatarireaders.core.ui.theme.PrimaryBlack
import com.example.monogatarireaders.profile.ui.composables.collection_section.CollectionContainer
import com.example.monogatarireaders.profile.ui.composables.header_section.ProfileHeader
import com.example.monogatarireaders.profile.ui.composables.library_section.LibrarySection
import com.example.monogatarireaders.shared.ui.layouts.AppLayout

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