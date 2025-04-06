package com.example.monogatarireaders.profile.data.repositories

import com.example.monogatarireaders.R
import com.example.monogatarireaders.profile.domain.models.CollectibleItemModel

class CollectionRepository {

    fun getCollections(): List<CollectibleItemModel> {
        return collections
    }

    // mock data
    private val collections = listOf(
        CollectibleItemModel(
            id = "1",
            imageUrl = R.drawable.collection_placeholder,
            notificationCount = 5
        ),
        CollectibleItemModel(
            id = "2",
            imageUrl = R.drawable.collection_placeholder,
            notificationCount = 0
        ),
        CollectibleItemModel(
            id = "3",
            imageUrl = R.drawable.collection_placeholder,
            notificationCount = 0
        ),
        CollectibleItemModel(
            id = "4",
            imageUrl = R.drawable.collection_placeholder,
            notificationCount = 0
        ),
        CollectibleItemModel(
            id = "5",
            imageUrl = R.drawable.collection_placeholder,
            notificationCount = 0
        ),
        CollectibleItemModel(
            id = "6",
            imageUrl = R.drawable.collection_placeholder,
            notificationCount = 2
        ),
        CollectibleItemModel(
            id = "7",
            imageUrl = R.drawable.collection_placeholder,
            notificationCount = 0
        ),
    )
}