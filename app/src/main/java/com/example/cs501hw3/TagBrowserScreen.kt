package com.example.cs501hw3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.foundation.layout.ExperimentalLayoutApi


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagBrowserScreen(modifier: Modifier = Modifier) {
    val allTags = listOf(
        "Poker", "Fitness", "Coding", "AI", "Boston", "Food",
        "Travel", "Study", "Martial Arts", "BJJ", "Business", "BU"
    )

    val selected = remember { mutableStateListOf<String>() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Tag Browser", style = MaterialTheme.typography.titleLarge)

        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("All Tags", style = MaterialTheme.typography.titleMedium)

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    allTags.forEach { tag ->
                        val isSelected = selected.contains(tag)

                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                if (isSelected) selected.remove(tag) else selected.add(tag)
                            },
                            label = { Text(tag) }
                        )
                    }
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Selected Tags", style = MaterialTheme.typography.titleMedium)

                Button(
                    onClick = { selected.clear() },
                    enabled = selected.isNotEmpty(),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Clear")
                }

                if (selected.isEmpty()) {
                    Text(
                        text = "Tap tags above to select them.",
                        modifier = Modifier.padding(top = 8.dp)
                    )
                } else {
                    FlowColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        maxItemsInEachColumn = 4
                    ) {
                        selected.forEach { tag ->
                            AssistChip(
                                onClick = { selected.remove(tag) },
                                label = { Text(tag) }
                            )
                        }
                    }
                }
            }
        }
    }
}
