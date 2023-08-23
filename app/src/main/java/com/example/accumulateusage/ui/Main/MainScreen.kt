package com.example.accumulateusage.ui.theme

import android.Manifest
import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.accumulateusage.ui.Main.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    transitionDebug: () -> Unit = {},
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val context = LocalContext.current
    val usageList by viewModel.usageList.collectAsState()
    val workerFlag by viewModel.workerState.collectAsState()
    val scope = rememberCoroutineScope()


    LaunchedEffect(true){
        viewModel.checkWorkerState(context)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        SnackbarHost(hostState = snackbarHostState)
    }

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Accumulate Usage",
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp),
            style = Typography.h5,
            textAlign = TextAlign.Center
        )
        Text(
            text = "設定",
            modifier = modifier
                .align(Alignment.CenterHorizontally),
            color = MaterialTheme.colors.primary
        )

        Row(
            modifier = modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "使用履歴の定期収集"
            )
            Switch(checked = workerFlag, onCheckedChange = {
                scope.launch {
                    val str = viewModel.toggleWorker(context)
                    viewModel.checkWorkerState(context)
                    snackbarHostState.showSnackbar(message = str, actionLabel = null, duration = SnackbarDuration.Short)
                }
            })
        }

        SettingContent(
            modifier = modifier.align(Alignment.CenterHorizontally),
            title = "使用履歴の保存",
            description = "OSから最新の使用履歴を取得し保存します．",
            onClick = {
                viewModel.getLatestUsage(context)
            }
        )

        SettingContent(
            modifier = modifier.align(Alignment.CenterHorizontally),
            title = "使用履歴をエクスポート",
            description = "保存された使用履歴をDocumentsにエクスポートします．",
            onClick = {
                viewModel.saveUsage(context)
            }
        )

//        Button(
//            onClick = {
//                viewModel.checkWorkerState(context = context)
//            },
//            modifier = modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Text(text = "チェック")
//        }
//
//        Button(
//            onClick = {
//                viewModel.setWorkManager(context = context)
//            },
//            modifier = modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Text(text = "定期収集を開始する")
//        }
//        Button(
//            onClick = {
//                viewModel.getLatestUsage(context)
//            },
//            modifier = modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Text(text = "使用履歴の保存")
//        }
//
//        Button(
//            onClick = {
//                viewModel.saveUsage(context)
//            },
//            modifier = modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Text(text = "使用履歴をエクスポート")
//        }


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

@Composable
fun SettingContent(
    modifier: Modifier = Modifier.fillMaxSize(),
    title:String,
    description:String = "",
    onClick:() -> Unit = {},
) {
    Column(
        modifier = modifier
            .clickable {
                onClick()
            }
            .padding(vertical =12.dp)
    ) {
        Text(text = title)
        Text(
            text = description,
            style = Typography.caption,

        )
    }
}