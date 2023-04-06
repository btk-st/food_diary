package com.example.android.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodDao {
    @Insert
    fun insert(food: Food)

    @Query("SELECT * FROM food")
    fun getAll(): LiveData<List<Food>>

    @Query("SELECT * FROM food WHERE id = :id")
    fun getById(id: Long): Food?

    @Query("DELETE FROM food")
    fun deleteAll()
}