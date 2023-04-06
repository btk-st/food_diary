package com.example.android.trackmysleepquality

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val dailyCalories = sharedPreferences.getInt("DailyCalories", -1)

        if (dailyCalories == -1) {
            // Создание новой записи DailyCalories и сохранение ее в SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putInt("DailyCalories", 0)
            editor.apply()
        }
        setContentView(R.layout.activity_main)
    }
}
