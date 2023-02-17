package com.example.accumulateusage

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AccumulateUsageApp: Application(), Configuration.Provider {
    @Inject lateinit var workerFactory: HiltWorkerFactory

    companion object {
        lateinit var instance: AccumulateUsageApp private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}