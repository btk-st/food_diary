package com.example.android.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MealDao {
    @Insert
    fun insert(meal: Meal)

    @Query("SELECT * FROM meal")
    fun getAll(): LiveData<List<Meal>>

    @Query("SELECT * FROM meal WHERE id = :id")
    fun getById(id: Long): LiveData<Meal?>

//    @Transaction
//    suspend fun deleteMealAndEntriesById(mealId: Long) {
//        deleteEntriesByMealId(mealId)
//        deleteMealById(mealId)
//    }

    @Query(
        """
        SELECT meal.*, SUM(food.calories * COALESCE(meal_food_entries.quantity, 0)) AS calories
        FROM meal
        LEFT JOIN meal_food_entries ON meal.id = meal_food_entries.meal_id
        LEFT JOIN food ON meal_food_entries.food_id = food.id
        GROUP BY meal.id
        ORDER BY meal.date DESC
    """
    )
    fun getAllMealsWithCalories(): LiveData<List<MealWithCalories>>

    @Query(
        """
        SELECT SUM(food.calories * meal_food_entries.quantity)
        FROM meal_food_entries 
        INNER JOIN food ON meal_food_entries.food_id = food.id
        INNER JOIN meal ON meal.id = meal_food_entries.meal_id 
        WHERE meal.date BETWEEN :start AND :end
    """
    )
    fun getTodayCalories(start: Long, end: Long): LiveData<Long>


    @Query("DELETE FROM meal WHERE id = :mealId")
    suspend fun deleteMealById(mealId: Long)

    @Query("DELETE FROM meal_food_entries WHERE meal_id = :mealId")
    suspend fun deleteEntriesByMealId(mealId: Long)


}