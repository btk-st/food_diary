package com.example.android.trackmysleepquality.database

import androidx.room.*
import java.util.*

@Entity(tableName = "meal")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val date: Long = 0L

)
