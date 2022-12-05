package com.example.accumulateusage

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import com.example.accumulateusage.works.GetUsageWorker
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUsageWorkerTest {
    private lateinit var context: Context

    @Test
    fun whenLaunchDowork_thenSuccess(){
        val worker = TestListenableWorkerBuilder<GetUsageWorker>(
            context
        ).build()

        runBlocking {
            val result = worker.doWork()

            Truth.assertThat(result).isEqualTo(ListenableWorker.Result.success())
        }
    }
}