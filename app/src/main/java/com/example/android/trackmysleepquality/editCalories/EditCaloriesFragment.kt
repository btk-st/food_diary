package com.example.android.trackmysleepquality.editCalories

import android.R
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.trackmysleepquality.addMealFoodEntry.AddMealFoodEntryFragmentArgs
import com.example.android.trackmysleepquality.addMealFoodEntry.AddMealFoodEntryViewModel
import com.example.android.trackmysleepquality.addMealFoodEntry.AddMealFoodEntryViewModelFactory
import com.example.android.trackmysleepquality.database.AppDatabase
import com.example.android.trackmysleepquality.databinding.FragmentAddMealFoodEntryBinding
import com.example.android.trackmysleepquality.databinding.FragmentEditCaloriesBinding
import com.example.android.trackmysleepquality.foodDB.FoodDatabaseViewModel
import com.example.android.trackmysleepquality.foodDB.FoodDatabaseViewModelFactory

class EditCaloriesFragment : Fragment() {
    private lateinit var viewModel: EditCaloriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditCaloriesBinding.inflate(inflater, container, false)
        val application = requireNotNull(activity).application

        val viewModelFactory = EditCaloriesViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EditCaloriesViewModel::class.java)

        binding.caloriesInput.setText(viewModel.getDailyCalories().toString())
        binding.saveButton.setOnClickListener {
            viewModel.updateDailyCalories(binding.caloriesInput.text.toString().toInt())
            this.findNavController().navigateUp()
        }

        return binding.root
    }
}