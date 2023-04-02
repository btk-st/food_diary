package com.example.android.trackmysleepquality.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var name: String = "",
    val calories: Int = 0,
    @ColumnInfo(name = "serving_size")
    val servingSize: String = ""
)