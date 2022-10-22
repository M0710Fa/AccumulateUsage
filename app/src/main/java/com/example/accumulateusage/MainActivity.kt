package com.example.accumulateusage

import android.app.AppOpsManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isUsageStatsPermissionGranted: Boolean = checkUsageStatsPermission()
        var startDestination = "main"

        if(!isUsageStatsPermissionGranted){
            startDestination = "permissionDemand"
        }

        setContent {
            AppHost(startDestination = startDestination)
        }
    }

    fun requestPermission(){
        if(checkUsageStatsPermission()){
            Log.i(TAG,"UsageStats Permission is Granted")
        }else{
            Log.i(TAG,"UsageStats Permission is NOT Granted")
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))

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