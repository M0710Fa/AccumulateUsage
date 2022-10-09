package com.example.accumulateusage.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.accumulateusage.GetUsageStats
import com.example.accumulateusage.Main.MainViewModel

@Composable
fun MainScreen(
    usageStats: GetUsageStats,
    viewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val scroll = rememberScrollState(0)

    val usageList by viewModel.usageList.collectAsState()

    Column() {
        Text(
            text = "スマートフォンの使用履歴を記憶します",
        )
        Button(onClick = {
            viewModel.saveUsageStats(usageStats)
        }) {
            Text(text = "GetUsage")
        }
        Button(onClick = {
            viewModel.readUsage()
        }) {
            Text(text = "ReadUsage")
        }
        LazyColumn {
            items(usageList){
                Text(text = it)
            }
        }
    }
}