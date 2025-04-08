package com.monogatari.app.profile.ui.composables.collection_section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monogatari.app.core.ui.theme.PrimaryWhite
import com.monogatari.app.profile.domain.models.CollectibleItemModel

@Composable
fun CollectionContainer(
    collectibles: List<CollectibleItemModel> = emptyList(),
    onAddCollectibleClick: () -> Unit = {}
) {
    // We create a list of items that includes the collectibles and an "add" button
    val itemsList = collectibles.toMutableList().let { list ->
        list.add(CollectibleItemModel(id = "add_button"))
        list
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "My Collection",
                color = PrimaryWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "View All",
                color = PrimaryWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Text(
            text = "Your stickers and digital collectibles",
            color = Color.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 5.dp, bottom = 12.dp)
        )

        GridCollection(collectionList = itemsList, onAddCollectibleClick = onAddCollectibleClick)
    }
}
