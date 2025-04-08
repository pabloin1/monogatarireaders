package com.monogatari.app.manga_detail.ui.composables.chapter_section.sort_dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monogatari.app.core.ui.theme.BackgroundBlue
import com.monogatari.app.core.ui.theme.BorderGray
import com.monogatari.app.core.ui.theme.PrimaryWhite
import com.monogatari.app.manga_detail.domain.models.ChapterSortOrder

@Composable
fun SortDropdown(
    selectedOption: String,
    onOptionSelected: (ChapterSortOrder) -> Unit
) {
    val options = listOf(ChapterSortOrder.NEWEST, ChapterSortOrder.OLDEST)
    var expanded by remember { mutableStateOf(false) }

    Box {
        Row(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = BorderGray,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable { expanded = !expanded }
                .background(BackgroundBlue.copy(alpha = 0.7f), RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedOption,
                color = PrimaryWhite,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Sort",
                tint = PrimaryWhite,
                modifier = Modifier.size(20.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.value, fontSize = 12.sp) },
                    onClick = {
                        val selectedOrder = when (option) {
                            ChapterSortOrder.NEWEST -> ChapterSortOrder.NEWEST
                            ChapterSortOrder.OLDEST -> ChapterSortOrder.OLDEST
                        }
                        onOptionSelected(selectedOrder)
                        expanded = false
                    }
                )
            }
        }
    }

}
