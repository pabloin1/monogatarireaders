package com.monogatari.app.shared.domain.adapters

data class UserAdapter(
    val id: Long,
    val username: String,
    val displayName: String,
    val profileImageUrl: String
)