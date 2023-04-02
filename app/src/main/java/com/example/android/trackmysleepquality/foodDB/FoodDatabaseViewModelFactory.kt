package com.example.android.trackmysleepquality.foodDB

import android.app.Application
import com.example.android.trackmysleepquality.database.FoodDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FoodDatabaseViewModelFactory(
    private val dao: FoodDao,
    private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodDatabaseViewModel::class.java)) {
            return FoodDatabaseViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
