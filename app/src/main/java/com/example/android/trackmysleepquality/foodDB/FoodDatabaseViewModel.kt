package com.example.android.trackmysleepquality.foodDB

import android.app.Application
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.Food
import com.example.android.trackmysleepquality.database.FoodDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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
