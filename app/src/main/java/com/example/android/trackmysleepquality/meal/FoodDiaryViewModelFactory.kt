package com.example.android.trackmysleepquality.meal

import android.app.Application
import com.example.android.trackmysleepquality.database.MealDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FoodDiaryViewModelFactory(private val mealDao: MealDao,
                                private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodDiaryViewModel::class.java)) {
            return FoodDiaryViewModel(mealDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
