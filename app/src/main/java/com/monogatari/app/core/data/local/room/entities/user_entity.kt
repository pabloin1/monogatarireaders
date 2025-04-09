package com.monogatari.app.core.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.monogatari.app.core.domain.constants.ROOM_CONSTANTS

@Entity(tableName = ROOM_CONSTANTS.USER_TABLE)
data class UserEntity(
    @PrimaryKey val id: Long,
    val username: String,
    val displayName: String,
    val profileImageUrl: String?
)