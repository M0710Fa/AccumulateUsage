package com.example.accumulateusage.model.source.local

import android.app.usage.UsageStats
import android.content.Context

interface UsageFileDataSource {
    suspend fun readFile(fileName: String): String?
    suspend fun appendUsage(fileName: String, usageStats: List<UsageStats>)
}