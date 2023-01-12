package com.example.accumulateusage.model.source.localfiles

import android.app.usage.UsageStats
import android.content.Context
import android.util.Log
import com.example.accumulateusage.AccumulateUsageApp
import java.io.BufferedReader
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class UsageFileDataSourceImpl @Inject constructor(
): UsageFileDataSource {

    override suspend fun readFile(fileName: String): String? {
        val instance by lazy {
            AccumulateUsageApp.instance
        }
        val readFile = File(instance.filesDir, fileName)
        val result: String?

        if(!readFile.exists()){
            Log.d("debug","No file exists")
            result = null
        } else{
            result = readFile.bufferedReader().use(BufferedReader::readText)
            Log.d("debug",result)
        }

         return result
    }

    override suspend fun appendUsage(fileName: String, usageStats: List<UsageStats>) {
        var str = ""
        usageStats.forEach {
            if (it.totalTimeInForeground.toInt() != 0) {
                val date = Date(it.lastTimeUsed)
                val eDate = Date(it.firstTimeStamp)
                val format = SimpleDateFormat("yyyy.MM.dd, E, HH:mm")
                str += "${it.packageName},${format.format(date)},${it.totalTimeInForeground},${format.format(eDate)}\n"
                Log.d( "appendUsage", "packageName: ${it.packageName}, lastTimeUsed: ${Date(it.lastTimeUsed)}" + "totalTimeInForeground: ${it.totalTimeInForeground}")
            }
        }

        val openFile by lazy {
            AccumulateUsageApp.instance.openFileOutput(fileName, Context.MODE_PRIVATE)
        }

        openFile.use {
            it.write(str.toByteArray())
        }
    }
}