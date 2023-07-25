package com.example.accumulateusage.ui.theme

import android.Manifest
import android.os.Build
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
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
            Text(text = "定期収集を開始する")
        }
        Button(
            onClick = {
                viewModel.getLatestUsage(context)
            },
            modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "使用履歴の保存")
        }
        Button(
            onClick = {
                viewModel.saveUsage(context)
            },
            modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "使用履歴をエクスポート")
        }


        LazyColumn {
            items(usageList){
                Text(text = it)
            }
        }
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q){
            Column() {
                val externalStoragePermission = rememberPermissionState(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                if(externalStoragePermission.status.isGranted){
                    Text(text = "ストレージへのアクセス権限設定済み")
                }else {
                    Text(text = "ストレージへのアクセス権限が必要です")
                    Button(onClick = { externalStoragePermission.launchPermissionRequest() }) {
                        Text(text = "権限を設定")
                    }
                }
            }
        }
    }
}
