package com.example.accumulateusage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDemandScreen(
    openPermissionSetting: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "スマートフォンの使用履歴の取得には許可が必要です")
        Text(text = "設定を開いて，アプリに権限を与えてください")
        Button(
            onClick = {
                      openPermissionSetting()
                      },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "設定を開く")
        }
    }
}