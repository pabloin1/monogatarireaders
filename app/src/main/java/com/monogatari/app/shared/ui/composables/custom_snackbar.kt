package com.monogatari.app.shared.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monogatari.app.core.ui.theme.GlowingYellow
import com.monogatari.app.core.ui.theme.PrimaryBlack

@Composable
fun CustomSnackBar(text : String, action : @Composable () -> Unit = {}, modifier: Modifier = Modifier) {
    Snackbar(
        modifier = modifier.padding(16.dp),
        content = {
            Text(
                text = text,
                color = PrimaryBlack,
            )
        },
        containerColor = GlowingYellow,
        action = action,
    )
}