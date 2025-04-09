package com.monogatari.app.shared.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monogatari.app.core.ui.theme.BackgroundGrayItem
import com.monogatari.app.core.ui.theme.BorderGray
import com.monogatari.app.core.ui.theme.GlowingYellow
import com.monogatari.app.core.ui.theme.PrimaryWhite
import com.monogatari.app.router.data.NavigationData
import com.monogatari.app.router.data.states.LocalRouter
import com.monogatari.app.router.data.states.navigateTo

@Composable
fun OfflineMode() {
    val router = LocalRouter.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGrayItem, shape = RoundedCornerShape(16.dp))
            .border(
                width = 2.dp,
                color = BorderGray,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "OFFLINE MODE",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = GlowingYellow,
                letterSpacing = 2.sp
            )

            Text(
                text = "CONTINUE READING DOWNLOADED CONTENT",
                fontSize = 14.sp,
                color = PrimaryWhite,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            GlowingButton(
                text = "GO TO DOWNLOADS",
                onClick = { router.navigateTo(NavigationData.profile.label) },
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "TRY RECONNECTING",
                color = PrimaryWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}