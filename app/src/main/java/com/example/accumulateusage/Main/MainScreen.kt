package com.example.accumulateusage.ui.theme

import android.icu.lang.UScript.getUsage
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.accumulateusage.Main.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    Column() {
        Text(
            text = "スマートフォンの使用履歴を記憶します",
        )
        Button(onClick = { }) {
            Text(text = "GetUsage")
        }
    }
}