package com.monogatari.app.login.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monogatari.app.core.data.states.viewmodels.LocalViewModelProvider
import com.monogatari.app.core.ui.theme.BackgroundBlue
import com.monogatari.app.core.ui.theme.BackgroundGrayItem
import com.monogatari.app.core.ui.theme.GlowingYellow
import com.monogatari.app.core.ui.theme.PrimaryBlack
import com.monogatari.app.core.ui.theme.PrimaryWhite
import com.monogatari.app.login.domain.models.LoginState
import com.monogatari.app.register.domain.models.RegisterState
import com.monogatari.app.router.data.NavigationData
import com.monogatari.app.router.data.states.LocalRouter
import com.monogatari.app.router.data.states.navigateTo
import com.monogatari.app.shared.ui.composables.AuthButton
import com.monogatari.app.shared.ui.composables.AuthTextField
import com.monogatari.app.shared.ui.composables.CustomSnackBar
import com.monogatari.app.shared.ui.composables.MonogatariTitle

@Composable
fun LoginScreen() {
    val router = LocalRouter.current
    val viewmodel = LocalViewModelProvider.current.loginViewmodel
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            BackgroundBlue,
            BackgroundGrayItem
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            MonogatariTitle("Monogatari")

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Login to continue your story",
                color = PrimaryWhite,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            AuthTextField(
                value = viewmodel.username.value,
                onValueChange = { viewmodel.username.value = it },
                placeholder = "Username",
                leadingIcon = Icons.Default.Person
            )

            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = viewmodel.password.value,
                onValueChange = { viewmodel.password.value = it },
                placeholder = "Password",
                leadingIcon = Icons.Default.Lock,
                isPassword = true,
                passwordVisible = viewmodel.passwordVisible.value,
                onPasswordVisibilityChanged = { viewmodel.passwordVisible.value = it }
            )
            Spacer(modifier = Modifier.height(32.dp))
            AuthButton(
                text = "LOGIN",
                enabled = viewmodel.isLoginButtonEnabled(),
                onClick = { viewmodel.login() }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Register option
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Text(
                    "Don't have an account?",
                    color = PrimaryWhite,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Register",
                    color = GlowingYellow,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        router.navigateTo(
                            NavigationData.register
                        )
                    }
                )
            }
        }
        when (viewmodel.state.value) {
            is LoginState.Success -> {
                CustomSnackBar(
                    text = "Login successful",
                )
                router.navigateTo(NavigationData.discover.label)
                viewmodel.state.value = LoginState.Idle
            }
            is LoginState.Error -> {
                val errorMessage = (viewmodel.state.value as RegisterState.Error).message
                CustomSnackBar(text = errorMessage)
            }
            is LoginState.Loading -> {
                CircularProgressIndicator()
            }
            else -> {

            }
        }
    }
}