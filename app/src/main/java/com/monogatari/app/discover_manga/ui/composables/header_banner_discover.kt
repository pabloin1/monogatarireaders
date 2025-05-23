package com.monogatari.app.discover_manga.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monogatari.app.R
import com.monogatari.app.core.ui.theme.PrimaryBlack
import com.monogatari.app.core.ui.theme.PrimaryWhite
import com.monogatari.app.core.ui.theme.SecundaryGlowingYellow
import com.monogatari.app.shared.ui.composables.CustomOutlinedButton
import com.monogatari.app.shared.ui.composables.GlowingButton

@Composable
fun HeaderBannerDiscover(
    onStartReadingClick: () -> Unit,
    onContactUsClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.satoru_gojo),
            contentDescription = "Banner Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryBlack.copy(alpha = 0.6f))
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Text(
                text = "DISCOVER",
                color = PrimaryWhite,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        color = SecundaryGlowingYellow,
                        offset = Offset(0f, 0f),
                        blurRadius = 20f
                    )
                )
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "UNIQUE STORIES ANYTIME",
                color = PrimaryWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlowingButton(
                    modifier =  Modifier.weight(1f),
                    text = "START READING",
                    onClick = { onStartReadingClick() }
                )
                CustomOutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { onContactUsClick() },
                    text = "CONTACT US",
                )
            }
        }
    }
}
