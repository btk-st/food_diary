package com.example.android.trackmysleepquality.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.database.Meal
import com.example.android.trackmysleepquality.databinding.FragmentMealDiaryBinding
import java.time.LocalTime

class FoodDiaryFragment : Fragment() {

    private lateinit var viewModel: FoodDiaryViewModel
    private lateinit var binding: FragmentMealDiaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealDiaryBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val applicationContext = requireNotNull(this.activity).applicationContext
        val mealDao = com.example.android.trackmysleepquality.database.AppDatabase.getInstance(application).mealDao()

        val viewModelFactory = FoodDiaryViewModelFactory(mealDao, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FoodDiaryViewModel::class.java)
        val adapter = MealAdapter(applicationContext)

        binding.mealEntriesRecyclerView.adapter = adapter
        // Получаем список приемов пищи из базы данных и отображаем их на экране
        viewModel.mealsWithCalories.observe(viewLifecycleOwner) { mealsWithCalories ->
            if (mealsWithCalories != null) {
                adapter.data = mealsWithCalories
            }
        }

        // Добавляем обработчик нажатия на кнопку добавления приема пищи
        binding.addMealButton.setOnClickListener {
            // Открываем экран добавления приема пищи
            viewModel.addMeal(System.currentTimeMillis())
        }
        binding.toFoodDatabase.setOnClickListener {
            val action = FoodDiaryFragmentDirections.actionFoodDiaryFragmentToFoodDatabaseFragment()
            this.findNavController().navigate(action)
        }
        binding.toCaloriesEdit.setOnClickListener {
            val action = FoodDiaryFragmentDirections.actionFoodDiaryFragmentToEditCaloriesFragment()
            this.findNavController().navigate(action)
        }


        viewModel.todayCalories.observe(viewLifecycleOwner) {calories ->
            binding.caloriesTextView.text = viewModel.caloriesString(applicationContext)
        }


        return binding.root
    }
}
