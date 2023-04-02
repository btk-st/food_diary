package com.example.android.trackmysleepquality.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "meal_food_entries",
)
data class MealFoodEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "meal_id")
    val mealId: Long,
    @ColumnInfo(name = "food_id")
    var foodId: Long,
    var quantity: Double = 0.0
)