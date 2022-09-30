package com.example.accumulateusage.Main

import androidx.lifecycle.ViewModel
import com.example.accumulateusage.model.repository.UsageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val usageRepository: UsageRepository
): ViewModel() {

}