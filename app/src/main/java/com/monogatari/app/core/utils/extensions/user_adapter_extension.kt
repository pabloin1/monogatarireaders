package com.monogatari.app.core.utils.extensions

import com.monogatari.app.core.data.local.room.entities.UserEntity
import com.monogatari.app.shared.domain.adapters.UserAdapter

fun UserAdapter.toEntity() = UserEntity(
    id = id,
    username = username,
    displayName = displayName,
    profileImageUrl = profileImageUrl
)