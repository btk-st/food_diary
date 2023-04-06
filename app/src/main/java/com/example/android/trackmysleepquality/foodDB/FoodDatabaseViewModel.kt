package com.example.android.trackmysleepquality.foodDB

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.android.trackmysleepquality.database.FoodDao
import kotlinx.coroutines.Job

class FoodDatabaseViewModel(private val dao: FoodDao, application: Application) :
    AndroidViewModel(application) {

    init {
        Log.i("FoodDatabaseViewModel", "FoodDatabaseViewModel created")
    }

    private var viewModelJob = Job()
    val foods = dao.getAll()


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("FoodDatabaseViewModel", "FoodDatabaseViewModel destroyed")
    }


}
