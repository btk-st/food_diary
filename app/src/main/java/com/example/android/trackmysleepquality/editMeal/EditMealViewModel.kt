package com.example.android.trackmysleepquality.editMeal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.android.trackmysleepquality.database.MealDao
import com.example.android.trackmysleepquality.database.MealFoodEntry
import com.example.android.trackmysleepquality.database.MealFoodEntryDao
import kotlinx.coroutines.*

class EditMealViewModel(
    private val mealFoodEntryDao: MealFoodEntryDao,
    private val mealDao: MealDao,
    application: Application,
    mealId:Long
) : AndroidViewModel(application) {

    val mealFoodEntries = mealFoodEntryDao.getByMealId(mealId)
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    fun saveMealFoodEntry(mealId: Long, foodId: Long):Long {
        val toSaveMeal = MealFoodEntry(mealId=mealId, foodId=foodId)
        return runBlocking {
            insert(toSaveMeal)
        }
    }
    private suspend fun insert(mealFoodEntry: MealFoodEntry):Long {
        return withContext(Dispatchers.IO) {
            mealFoodEntryDao.insert(mealFoodEntry)
        }
    }
    fun deleteMeal(mealId:Long) {
        uiScope.launch {
            delete(mealId)
        }
    }
    private suspend fun delete(mealId: Long) {
        withContext(Dispatchers.IO) {
            mealDao.deleteEntriesByMealId(mealId)
            mealDao.deleteMealById(mealId)
        }
    }
}

