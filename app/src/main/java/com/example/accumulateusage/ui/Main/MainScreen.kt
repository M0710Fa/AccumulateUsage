package com.example.accumulateusage.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.accumulateusage.ui.Main.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    transitionDebug: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val usageList by viewModel.usageList.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "スマートフォンの使用履歴を記憶します",
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )

        Button(
            onClick = {
                viewModel.setWorkManager(context = context)
            },
            modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Set Worker to Get Usage")
        }
        Button(
            onClick = {
                viewModel.readUsage()
            },
            modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "ReadUsage")
        }

        Button(
            onClick = {
                transitionDebug()
            },
            modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "デバッグ")
        }

        LazyColumn {
            items(usageList){
                Text(text = it)
            }
        }
    }
}
