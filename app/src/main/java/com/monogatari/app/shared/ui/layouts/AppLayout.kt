package com.monogatari.app.shared.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monogatari.app.core.ui.theme.PrimaryBlack
import com.monogatari.app.router.ui.composables.BottomNavigationBar

@Composable
fun AppLayout(
    children : @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize().background(PrimaryBlack)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp)
        ) {
            children()
        }
        BottomNavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(100.dp),
        )
    }
}