package com.example.accumulateusage.ui.Main

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
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
    private val fileName = "outer.txt"

    private var _usageList = MutableStateFlow<List<String>>(emptyList())
    val usageList = _usageList.asStateFlow()

    private var _workerState = MutableStateFlow<Boolean>(false)
    val workerState = _workerState.asStateFlow()

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

    fun getLatestUsage(context: Context){
        viewModelScope.launch {
            try{
                val usages = GetUsageStats(context).getUsageStats()
                usageRepository.appendUsage("iiii.txt", usages)
                Log.i("mainViewModel", "Success get Usage")
            }catch (e: Exception){
                Log.i("mainViewModel", "Failed get Usage $e")
            }
        }
    }

    fun saveUsage(context: Context){
        viewModelScope.launch {
            try{
                val usages = GetUsageStats(context).getUsageStats()
                usageRepository.saveUsage(usages)
                Log.i("mainViewModel", "External")
                Toast.makeText(context, "エクスポート完了", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Log.i("mainViewModel", "Failed External $e")
                Toast.makeText(context, "エクスポート失敗", Toast.LENGTH_SHORT).show()
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
        Log.d("worker", "Set WorkManager")
    }

    fun cancelWorkManager(context: Context){
        WorkManager.getInstance(context).cancelUniqueWork(GetUsageWorker.WORK_NAME)
        Log.d("worker", "Cancel WorkManager")
    }

    fun checkWorkerState(context: Context){
        val works = WorkManager.getInstance(context).getWorkInfosForUniqueWork(GetUsageWorker.WORK_NAME).get()
        Log.d("チェック", works.toString())
        works.map {
            val status = it.state
            _workerState.value = status == WorkInfo.State.ENQUEUED || status == WorkInfo.State.RUNNING
        }
    }

    fun toggleWorker(context: Context):String {
        if(_workerState.value){
            cancelWorkManager(context)
            return "履歴収集を停止しました"
        }else{
            setWorkManager(context)
            return "履歴収集を開始します"
        }
    }
}
