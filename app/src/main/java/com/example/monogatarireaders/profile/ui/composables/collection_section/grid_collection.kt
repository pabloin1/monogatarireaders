package com.example.monogatarireaders.profile.ui.composables.collection_section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.monogatarireaders.profile.domain.models.CollectibleItemModel

@Composable
fun GridCollection(collectionList : List<CollectibleItemModel>, onAddCollectibleClick : () -> Unit) {

    val chunkedItems = collectionList.chunked(4)

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        chunkedItems.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { item ->
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (item.id == "add_button") {
                            AddCollectibleButton(onClick = {onAddCollectibleClick()})
                        } else {
                            CollectibleItem(collectible = item)
                        }
                    }
                }

                // Add empty boxes if the row is not complete to maintain uniform spacing
                repeat(4 - rowItems.size) {
                    Box(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}