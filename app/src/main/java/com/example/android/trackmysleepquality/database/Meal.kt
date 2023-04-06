package com.example.android.trackmysleepquality.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val date: Long = 0L

)
