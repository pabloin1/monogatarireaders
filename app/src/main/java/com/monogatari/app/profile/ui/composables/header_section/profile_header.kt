package com.monogatari.app.profile.ui.composables.header_section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monogatari.app.R
import com.monogatari.app.core.ui.theme.PrimaryBlack

@Composable
fun ProfileHeader(
    username : String,
    chaptersRead : Int = 0,
    favorites : Int = 0,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF2962FF),
                            Color(0xFF7B1FA2)
                        )
                    )
                )
        )

        // Profile Avatar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 75.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.satoru_gojo),
                contentDescription = "Profile Avatar",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(2.dp, PrimaryBlack, CircleShape)
                    .background(Color.Black),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = username,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "Premium Member",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 2.dp),
                fontWeight = FontWeight.Bold
            )
        }

        // User stats
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 320.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem("$243", "Donations")
            HorizontalDivider(
                modifier = Modifier
                    .height(40.dp)
                    .width(2.dp)
                    .background(Color.DarkGray)
            )
            StatItem(chaptersRead.toString(), "Chapters Read")
            HorizontalDivider(
                modifier = Modifier
                    .height(40.dp)
                    .width(2.dp)
                    .background(Color.DarkGray)
            )
            StatItem(favorites.toString(), "Favorites")
        }
    }
}
