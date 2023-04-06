package com.example.android.trackmysleepquality.editCalories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EditCaloriesViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditCaloriesViewModel::class.java)) {
            return EditCaloriesViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}