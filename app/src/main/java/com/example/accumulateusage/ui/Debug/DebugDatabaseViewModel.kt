package com.example.accumulateusage.ui.Debug

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.accumulateusage.model.repository.UsageRepository
import com.example.accumulateusage.model.source.local.Usage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebugDatabaseViewModel @Inject constructor(
    private val usageRepository: UsageRepository
): ViewModel(){

    private var _usages =  MutableStateFlow<List<Usage>>(emptyList())
    val usages : StateFlow<List<Usage>> = _usages

    fun addUsage(usage: Usage) = viewModelScope.launch {
        try {
            usageRepository.addUsage(usage)
            Log.d("debug", "Inserted")
        }catch (e: Exception){
            Log.d("debug", "Insert Error: $e")
        }
    }

    fun getUsages() = viewModelScope.launch {
        try {
            usageRepository.getUsages().collect {
                _usages.value = it
            }
            Log.d("debug", "Inserted")
        }catch (e: Exception){
            Log.d("debug", "Insert Error: $e")
        }
    }
}