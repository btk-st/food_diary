package com.example.android.trackmysleepquality.addFood

import android.app.Application
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.database.Food
import com.example.android.trackmysleepquality.database.FoodDao
import kotlinx.coroutines.*

class AddFoodViewModel(private val dao: FoodDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)


     fun addFood(name: String, calories: Int, servingSize: String) {
        val newFood = Food(name = name, calories = calories, servingSize = servingSize)
        uiScope.launch {
            insert(newFood)
        }
    }

    private suspend fun insert(food: Food) {
        withContext(Dispatchers.IO) {
            dao.insert(food)
        }
    }

}
