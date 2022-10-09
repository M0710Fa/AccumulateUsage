package com.example.accumulateusage

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.util.Log
import java.util.*

class GetUsageStats(
    private val context: Context
    ) {
    private val TAG = "GetUsageStats"

    fun getUsageStats(): List<UsageStats>{
        Log.i(TAG,"Accessed GetUsageStatsClass")
        return sortedUsageStatsString( getAppUsageStats())
        //return sortedUsageStatsString( getAppUsageStats())
    }

    //UsageStatsオブジェクトの取得
    private fun getAppUsageStats(): MutableList<UsageStats> {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -30)

        // usageStatsManagerのオブジェクトの取得
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        return usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            cal.timeInMillis,
            System.currentTimeMillis()
        )
    }

    private fun sortedUsageStatsString(usageStats: MutableList<UsageStats>): List<UsageStats> {
        usageStats.sortWith(
            compareBy { it.lastTimeUsed }
        )
        return usageStats
    }
}