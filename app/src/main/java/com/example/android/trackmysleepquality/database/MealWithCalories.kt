package com.example.android.trackmysleepquality.database

import androidx.room.Embedded

data class MealWithCalories(
    @Embedded val meal: Meal,
    val calories: Long
)