package com.example.accumulateusage.ui.Main

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.accumulateusage.GetUsageStats
import com.example.accumulateusage.model.repository.UsageRepository
import com.example.accumulateusage.works.GetUsageWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val usageRepository: UsageRepository
): ViewModel() {
    private val fileName = "output.txt"

    private var _usageList = MutableStateFlow<List<String>>(emptyList())
    val usageList = _usageList.asStateFlow()

    fun saveUsageStats(usageStats: GetUsageStats) = viewModelScope.launch {
        val getUsageStats = usageStats.getUsageStats()
        try {
            usageRepository.appendUsage(fileName, getUsageStats)
            Log.i("mainViewModel", "Success Save Usage")
        }catch (e: Exception){
            Log.i("mainViewModel", "Failed Save Usage $e")
        }
    }

    fun readUsage() {
        viewModelScope.launch {
            try {
                usageRepository.getUsage(fileName)?.collect{
                    _usageList.value = it
                }
                Log.i("mainViewModel", "Success Read Usage")
            }catch (e: Exception){
                Log.i("mainViewModel", "Failed Read Usage $e")
            }
        }
    }

    fun setWorkManager(context: Context){
        val request = PeriodicWorkRequestBuilder<GetUsageWorker>(24, TimeUnit.HOURS)
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            GetUsageWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
        Toast.makeText(context, "Success set worker!", Toast.LENGTH_LONG).show()
    }
}
