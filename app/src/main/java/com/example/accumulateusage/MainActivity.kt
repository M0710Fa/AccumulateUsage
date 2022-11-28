package com.example.accumulateusage

import android.app.AppOpsManager
import android.os.Build
import android.os.Bundle
import android.os.Process
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isUsageStatsPermissionGranted: Boolean = checkUsageStatsPermission()
        var startDestination = Destination.MainScreen.route

        if(!isUsageStatsPermissionGranted){
            startDestination = Destination.PermissionDemandScreen.route
        }

        setContent {
            AppHost(startDestination = startDestination)
        }
    }

    private fun checkUsageStatsPermission():Boolean{

        val appOpsManager = getSystemService(APP_OPS_SERVICE) as AppOpsManager
        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            appOpsManager.unsafeCheckOpNoThrow("android:get_usage_stats", Process.myUid(), packageName)
        }else {
            appOpsManager.checkOpNoThrow("android:get_usage_stats", Process.myUid(), packageName)
        }
        return mode == AppOpsManager.MODE_ALLOWED

    }

}