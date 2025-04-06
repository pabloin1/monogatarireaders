package com.example.monogatarireaders.not_found.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monogatarireaders.R
import com.example.monogatarireaders.core.ui.theme.BorderGray
import com.example.monogatarireaders.core.ui.theme.GlowingYellow
import com.example.monogatarireaders.shared.ui.composables.GlowingButton

@Composable
fun NotFoundScreen(onBackClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(90.dp))
                    .background(BorderGray),
                color = Color.Transparent,
                shadowElevation = 8.dp,
                shape = RoundedCornerShape(90.dp),

            ) {
                Image(
                    painter = painterResource(id = R.drawable.monogatari_logo),
                    contentDescription = "404 Character",
                    modifier = Modifier
                        .padding(20.dp)
                        .size(180.dp)
                        .clip(RoundedCornerShape(90.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "404",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = GlowingYellow
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¡Oops! Esta página se perdió en otra dimensión",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Nuestros héroes están trabajando para traerla de vuelta.",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            GlowingButton(
                text = "Volver a Inicio",
                onClick = onBackClicked,
                blurInitialValue = 4f,
                blurTargetValue = 20f
            )
        }
    }
}