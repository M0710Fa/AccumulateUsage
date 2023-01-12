package com.example.accumulateusage.model.repository

import android.app.usage.UsageStats
import com.example.accumulateusage.model.source.localfiles.UsageFileDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsageRepositoryImpl @Inject constructor(
    private val usageFileDataSource: UsageFileDataSource,
): UsageRepository {
    override suspend fun getUsage(fileName: String): Flow<List<String>>? {
        val usageString = usageFileDataSource.readFile(fileName)
        var usageList: List<String>
        return flow {
            if (!usageString.isNullOrEmpty()){
                usageList = usageString.split("\r\n","\n")
                emit(usageList)
            }
        }
    }

    override suspend fun appendUsage(fileName: String, usageStats: List<UsageStats>) {
        return usageFileDataSource.appendUsage(fileName, usageStats)
    }
}