package com.monogatari.app.login.domain.adapters

data class UserProfileAdapter(
    val createdAt: String,
    val displayName: String,
    val email: String,
    val id: Int,
    val lastLogin: String,
    val role: String,
    val username: String
)