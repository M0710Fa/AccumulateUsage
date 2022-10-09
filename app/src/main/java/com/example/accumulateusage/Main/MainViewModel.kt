package com.example.accumulateusage.Main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.accumulateusage.GetUsageStats
import com.example.accumulateusage.model.repository.UsageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val usageRepository: UsageRepository
): ViewModel() {
    fun saveUsageStats(usageStats: GetUsageStats) = viewModelScope.launch {
        val getUsageStats = usageStats.getUsageStats()
        try {
            usageRepository.appendUsage("output.txt", getUsageStats)
            Log.i("mainViewModel", "Success Save Usage")
        }catch (e: Exception){
            Log.i("mainViewModel", "Failed Save Usage $e")
        }
    }

    fun readUsage(): String? {
        var result: String? = null
        viewModelScope.launch {
            try {
                result = usageRepository.getUsage("output.txt")
                Log.i("mainViewModel", "Success Read Usage")
            }catch (e: Exception){
                Log.i("mainViewModel", "Failed Read Usage $e")
                result = "error"
            }
        }
        return result
    }
}