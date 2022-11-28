package com.example.accumulateusage.works

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.accumulateusage.GetUsageStats
import com.example.accumulateusage.model.repository.UsageRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class GetUsageWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val usageRepository: UsageRepository,
): CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "com.example.accumulateusage.works.GetUsageWorker"
    }

    private val fileName = "data.txt"
    private val context = appContext

    override suspend fun doWork(): Result {
        try {
            Log.i("Worker","Work request for sync is run")
            val usageStats = GetUsageStats(context).getUsageStats()
            usageRepository.appendUsage(fileName, usageStats)
        }catch (e: Exception){
            Log.i("Worker","Error: $e")
        }
        return Result.success()
    }
}