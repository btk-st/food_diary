package com.example.android.trackmysleepquality.editCalories

import android.app.Application
import android.preference.PreferenceManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.android.trackmysleepquality.database.FoodDao

class EditCaloriesViewModel (application: Application) :
    AndroidViewModel(application) {
    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    fun updateDailyCalories(dailyCalories: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("DailyCalories", dailyCalories)
        editor.apply()
    }

    fun getDailyCalories(): Int {
        return sharedPreferences.getInt("DailyCalories", 0)
    }
}