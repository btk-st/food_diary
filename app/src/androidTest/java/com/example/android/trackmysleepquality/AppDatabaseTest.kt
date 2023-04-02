
package com.example.android.trackmysleepquality

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.trackmysleepquality.database.*
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var foodDao: FoodDao
    private lateinit var mealDao: MealDao
    private lateinit var mealFoodEntryDao: MealFoodEntryDao
    private lateinit var db: AppDatabase


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        foodDao = db.foodDao()
        mealDao = db.mealDao()
        mealFoodEntryDao = db.mealFoodEntryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    @Throws(Exception::class)
    fun insertAndCheckFood() {
        val food1 = Food(1, "chicken", 100, "100 gramm")

        foodDao.insert(food1)

        val foods = foodDao.getById(1)

        assertEquals("chicken", foods?.name)


    }
}

