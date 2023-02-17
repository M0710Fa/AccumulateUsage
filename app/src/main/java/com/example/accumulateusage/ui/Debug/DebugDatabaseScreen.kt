package com.example.accumulateusage.ui.Debug

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.accumulateusage.model.source.local.Usage
import java.util.*

@Composable
fun DebugDatabaseScreen(
    viewModel: DebugDatabaseViewModel = hiltViewModel()
) {

    val usages by viewModel.usages.collectAsState()

    Column() {
        Text(text = "Roomデータベース")

        Button(
            onClick = {
                val usage = Usage(id = 0, name = "usage", date = Date(System.currentTimeMillis()), time = 100)
                viewModel.addUsage(usage)
            }
        ) {
            Text(text = "モックデータの追加")
        }

        Button(
            onClick = { viewModel.getUsages() }
        ) {
            Text(text = "データ表示")
        }

        LazyColumn {
            items(usages){
                Text(text = it.toString())
            }
        }
    }
}