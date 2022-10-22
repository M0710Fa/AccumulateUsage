package com.example.accumulateusage.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.accumulateusage.AccumulateUsageApp
import com.example.accumulateusage.GetUsageStats
import com.example.accumulateusage.Main.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val usageList by viewModel.usageList.collectAsState()

    Surface(
        modifier = modifier.fillMaxHeight().fillMaxWidth()
    ) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = "スマートフォンの使用履歴を記憶します",
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )

            Button(
                onClick = {
                    viewModel.saveUsageStats(GetUsageStats(context = AccumulateUsageApp.instance))
                },
                modifier = modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "GetUsage")
            }
            Button(
                onClick = {
                    viewModel.readUsage()
                },
                modifier = modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "ReadUsage")
            }

            LazyColumn {
                items(usageList){
                    Text(text = it)
                }
            }
        }
    }

}