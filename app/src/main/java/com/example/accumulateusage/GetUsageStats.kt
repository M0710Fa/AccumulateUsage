package com.example.accumulateusage

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class GetUsageStats(private val context: Context) {
    private val TAG = "GetUsageStats"

    fun getUsageStatsString(){
        Log.i(TAG,"Accessed GetUsageStatsClass")
        sortedUsageStatsString( getAppUsageStats())
        //return sortedUsageStatsString( getAppUsageStats())
    }

    //UsageStatsオブジェクトの取得
    private fun getAppUsageStats(): MutableList<UsageStats> {
        val cal = Calendar.getInstance()
        cal.add(Calendar.HOUR_OF_DAY, -25)

        // usageStatsManagerのオブジェクトの取得
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        return usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            cal.timeInMillis,
            System.currentTimeMillis()
        )
    }

    private fun sortedUsageStatsString(usageStats: MutableList<UsageStats>): String {
        var str =""

        usageStats.sortWith(
            compareBy { it.lastTimeUsed }
        )
        usageStats.forEach {
            if (it.totalTimeInForeground.toInt() != 0) {
                val date = Date(it.lastTimeUsed)
                val eDate = Date(it.firstTimeStamp)
                val format = SimpleDateFormat("yyyy.MM.dd, E, HH:mm")
                str += "${it.packageName},${format.format(date)},${it.totalTimeInForeground},${format.format(eDate)}\n"
                Log.d( TAG, "packageName: ${it.packageName}, lastTimeUsed: ${Date(it.lastTimeUsed)}" + "totalTimeInForeground: ${it.totalTimeInForeground}")
            }
        }
        return str
    }
}