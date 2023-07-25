package com.example.accumulateusage

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.util.Log
import java.util.Calendar

private const val TAG = "GetUsageStats"

class GetUsageStats(
    private val context: Context
    ) {

    fun getUsagesOneDay(): List<UsageStats>{
        Log.i(TAG,"Accessed GetUsageStatsClass")
        return sortedUsageStats( getAppUsageStatsOneDay())
    }

    fun getUsageStats(): List<UsageStats>{
        Log.i(TAG,"Accessed GetUsageStatsClass")
        return sortedUsageStats( getAppUsageStats())
    }

    //UsageStatsオブジェクトの取得
    private fun getAppUsageStats(): MutableList<UsageStats> {
        val cal = Calendar.getInstance()
        cal.add(Calendar.MONTH, -2)

        // usageStatsManagerのオブジェクトの取得
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        return usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            cal.timeInMillis,
            System.currentTimeMillis()
        )
    }

    private fun getAppUsageStatsOneDay(): MutableList<UsageStats> {
        val cal = Calendar.getInstance()
        cal.add(Calendar.HOUR_OF_DAY, -24)

        // usageStatsManagerのオブジェクトの取得
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        return usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            cal.timeInMillis,
            System.currentTimeMillis()
        )
    }

    private fun sortedUsageStats(usageStats: MutableList<UsageStats>): List<UsageStats> {
        usageStats.sortWith(
            compareBy { it.lastTimeUsed }
        )
        return usageStats
    }
}