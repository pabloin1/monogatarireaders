package com.example.monogatarireaders.profile.domain.models

data class CollectibleItemModel (
    val id: String,
    val imageUrl: Int? = null,
    val notificationCount: Int = 0
)