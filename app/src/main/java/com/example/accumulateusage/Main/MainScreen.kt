package com.example.accumulateusage.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    getUsage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column() {
        Text(
            text = "スマートフォンの使用履歴を記憶します",
        )
        Button(onClick = { getUsage() }) {
            Text(text = "GetUsage")
        }
    }
}