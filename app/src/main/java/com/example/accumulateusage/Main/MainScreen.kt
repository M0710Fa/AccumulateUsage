package com.example.accumulateusage.ui.theme

import androidx.compose.foundation.layout.Column
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
    var usageHistory: String? by remember {
        mutableStateOf(null)
    }

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
            usageHistory = viewModel.readUsage()
        }) {
            Text(text = "readUsage")
        }
        Text(text = usageHistory?:"no data")
    }
}