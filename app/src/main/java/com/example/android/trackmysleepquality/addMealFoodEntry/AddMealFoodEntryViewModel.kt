package com.example.android.trackmysleepquality.addMealFoodEntry

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.android.trackmysleepquality.database.MealFoodEntryDao
import kotlinx.coroutines.*

class AddMealFoodEntryViewModel(private val dao: MealFoodEntryDao, private val mealEntryId: Long, application: Application) :
    AndroidViewModel(application) {

    val mealFoodEntry = dao.getById(mealEntryId)

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    fun saveMealFoodEntry(quantity: Double) {
        uiScope.launch {
            update(quantity)
        }
    }
    fun deleteMealEntry() {
        uiScope.launch {
            delete()
        }
    }
    private suspend fun delete() {
        withContext(Dispatchers.IO) {
            mealFoodEntry.value?.let { dao.delete(it) }
        }
    }
    private suspend fun update(quantity: Double) {
        withContext(Dispatchers.IO) {
            mealFoodEntry.value?.quantity = quantity
            mealFoodEntry.value?.let { dao.update(it) }
        }
    }


}