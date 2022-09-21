package com.example.accumulateusage.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    Box() {
        Text(
            text = "Hello!!",
            modifier = modifier.align(Alignment.Center)
        )
    }
}