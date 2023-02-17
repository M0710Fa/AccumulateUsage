package com.example.accumulateusage.ui.Debug

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun DebugScreen() {
    Column() {
        TopAppBar(
            title = { Text(text = "デバッグスクリーン") }
        )
        DebugDatabaseScreen()
    }
}