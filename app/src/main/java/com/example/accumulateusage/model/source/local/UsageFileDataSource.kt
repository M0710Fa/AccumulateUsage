package com.example.accumulateusage.model.source.local

import android.app.usage.UsageStats

interface UsageFileDataSource {
    suspend fun readFile(fileName: String): String?
    suspend fun appendUsage(fileName: String, usageStats: List<UsageStats>)
}