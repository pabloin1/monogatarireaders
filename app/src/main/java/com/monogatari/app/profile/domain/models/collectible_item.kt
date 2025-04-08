package com.monogatari.app.profile.domain.models

data class CollectibleItemModel (
    val id: String,
    val imageUrl: Int? = null,
    val notificationCount: Int = 0
)