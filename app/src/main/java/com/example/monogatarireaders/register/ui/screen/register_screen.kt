package com.example.monogatarireaders.register.ui.screen

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
import com.example.monogatarireaders.core.data.states.viewmodels.LocalViewModelProvider
import com.example.monogatarireaders.core.ui.theme.BackgroundBlue
import com.example.monogatarireaders.core.ui.theme.BackgroundGrayItem
import com.example.monogatarireaders.core.ui.theme.GlowingYellow
import com.example.monogatarireaders.core.ui.theme.PrimaryBlack
import com.example.monogatarireaders.core.ui.theme.PrimaryWhite
import com.example.monogatarireaders.register.domain.models.RegisterState
import com.example.monogatarireaders.router.data.NavigationData
import com.example.monogatarireaders.router.data.states.LocalRouter
import com.example.monogatarireaders.router.data.states.navigateTo
import com.example.monogatarireaders.shared.ui.composables.AuthButton
import com.example.monogatarireaders.shared.ui.composables.AuthTextField
import com.example.monogatarireaders.shared.ui.composables.CustomSnackBar
import com.example.monogatarireaders.shared.ui.composables.MonogatariTitle

@Composable
fun RegisterScreen() {
    val router = LocalRouter.current
    val viewmodel = LocalViewModelProvider.current.registerViewModel
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
            Spacer(modifier = Modifier.height(36.dp))

            // Stylized title
            MonogatariTitle("Monogatari")

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Begin your new story",
                color = PrimaryWhite,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Username field
            AuthTextField(
                value = viewmodel.username.value,
                onValueChange = { viewmodel.username.value = it },
                placeholder = "Username",
                leadingIcon = Icons.Default.Person
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email field
            AuthTextField(
                value = viewmodel.email.value,
                onValueChange = { viewmodel.email.value = it },
                placeholder = "Email",
                leadingIcon = Icons.Default.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password field
            AuthTextField(
                value = viewmodel.password.value,
                onValueChange = { viewmodel.password.value = it },
                placeholder = "Password",
                leadingIcon = Icons.Default.Lock,
                isPassword = true,
                passwordVisible = viewmodel.passwordVisible.value,
                onPasswordVisibilityChanged = { viewmodel.passwordVisible.value = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Password field
            AuthTextField(
                value = viewmodel.confirmPassword.value,
                onValueChange = { viewmodel.confirmPassword.value = it },
                placeholder = "Confirm Password",
                leadingIcon = Icons.Default.Lock,
                isPassword = true,
                passwordVisible = viewmodel.confirmPasswordVisible.value,
                onPasswordVisibilityChanged = { viewmodel.confirmPasswordVisible.value = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Register button
            AuthButton(
                text = "REGISTER",
                enabled = viewmodel.isRegisterButtonEnabled(),
                onClick = { viewmodel.registerUser() }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Login option
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Text(
                    "Already have an account?",
                    color = PrimaryWhite,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    "Login",
                    color = GlowingYellow,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        router.navigateTo(
                            NavigationData.login
                        )
                    }
                )
            }

            when (viewmodel.state.value) {
                is RegisterState.Success -> {
                    CustomSnackBar(
                        text = "Registration successful",
                        action = {
                            Text(
                                text = "Login",
                                color = PrimaryBlack,
                                modifier = Modifier.clickable {
                                    router.navigateTo(NavigationData.login)
                                }
                            )
                        }
                    )
                }
                is RegisterState.Error -> {
                    val errorMessage = (viewmodel.state.value as RegisterState.Error).message
                    CustomSnackBar(text = errorMessage)
                }
                is RegisterState.Loading -> {
                    CircularProgressIndicator()
                }
                else -> {

                }
            }
        }
    }
}