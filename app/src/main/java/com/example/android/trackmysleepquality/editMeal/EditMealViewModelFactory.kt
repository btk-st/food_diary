package com.example.android.trackmysleepquality.editMeal

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.database.FoodDao
import com.example.android.trackmysleepquality.database.MealDao
import com.example.android.trackmysleepquality.database.MealFoodEntryDao
import com.example.android.trackmysleepquality.foodDB.FoodDatabaseViewModel


class EditMealViewModelFactory(
    private val mealFoodEntryDao: MealFoodEntryDao,
    private val mealDao: MealDao,
    private val application: Application,
    private val mealId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditMealViewModel::class.java)) {
            return EditMealViewModel(mealFoodEntryDao, mealDao, application, mealId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}