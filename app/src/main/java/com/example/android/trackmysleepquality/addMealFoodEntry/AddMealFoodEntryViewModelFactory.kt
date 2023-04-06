package com.example.android.trackmysleepquality.addMealFoodEntry

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.database.MealFoodEntryDao

class AddMealFoodEntryViewModelFactory (
    private val dao: MealFoodEntryDao,
    private val mealEntryId: Long,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddMealFoodEntryViewModel::class.java)) {
            return AddMealFoodEntryViewModel(dao, mealEntryId, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}