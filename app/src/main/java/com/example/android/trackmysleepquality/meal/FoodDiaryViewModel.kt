package com.example.android.trackmysleepquality.meal;

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.Meal
import com.example.android.trackmysleepquality.database.MealDao
import kotlinx.coroutines.*
import java.util.*
import kotlin.math.abs


class FoodDiaryViewModel(private val mealDao: MealDao, application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)
    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    val todayCalories = populateCalories()
    val mealsWithCalories = mealDao.getAllMealsWithCalories()

    private fun populateCalories(): LiveData<Long> {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            clear(Calendar.MINUTE)
            clear(Calendar.SECOND)
            clear(Calendar.MILLISECOND)
        }
        val startOfDay = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val endOfDay = calendar.timeInMillis
        return mealDao.getTodayCalories(startOfDay, endOfDay)
    }
    fun addMeal(date: Long) {
        val newMeal = Meal(date=date)
        uiScope.launch {
            insert(newMeal)
        }
    }
    private suspend fun insert(meal: Meal) {
        withContext(Dispatchers.IO) {
            mealDao.insert(meal)
        }
    }
    private fun getDailyCalories(): Long {
        return sharedPreferences.getInt("DailyCalories", 0).toLong()
    }
    fun caloriesString(context : Context): String {
        val dailyCalories = getDailyCalories()
        val todayCalories = todayCalories.value ?: 0

        return if (dailyCalories == todayCalories) {
            return context.getString(R.string.today_calories, todayCalories) +
                    '\n' + context.getString(R.string.daily_calories)
        } else {
            context.getString(R.string.today_calories, todayCalories) +
                    '\n' +
                    when {
                        todayCalories > dailyCalories -> context.getString(R.string.calories_exceeded, abs(todayCalories-dailyCalories))
                        todayCalories < dailyCalories -> context.getString(R.string.calories_remaining, abs(todayCalories-dailyCalories))
                        else -> ""
                    }
        }
    }



}
