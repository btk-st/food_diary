package com.example.android.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MealFoodEntryDao {
    @Insert
    fun insert(mealFoodEntry: MealFoodEntry):Long

    @Update
    fun update(mealFoodEntry: MealFoodEntry)

    @Query("SELECT * FROM meal_food_entries WHERE id = :id")
    fun getById(id: Long): LiveData<MealFoodEntry>

    @Query("SELECT * FROM meal_food_entries WHERE meal_id = :mealId")
    fun getByMealId(mealId: Long): LiveData<List<MealFoodEntry>>

    @Delete
    fun delete(mealFoodEntry: MealFoodEntry)
}