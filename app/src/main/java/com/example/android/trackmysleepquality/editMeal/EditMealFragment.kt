package com.example.android.trackmysleepquality.editMeal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.android.trackmysleepquality.database.AppDatabase
import com.example.android.trackmysleepquality.databinding.FragmentEditMealBinding

class EditMealFragment : Fragment() {
    private val args: EditMealFragmentArgs by navArgs()
    private lateinit var viewModel: EditMealViewModel


    private lateinit var binding: FragmentEditMealBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEditMealBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this


        val application = requireNotNull(activity).application
        val mealFoodEntryDao = AppDatabase.getInstance(application).mealFoodEntryDao()
        val mealDao = AppDatabase.getInstance(application).mealDao()

        val viewModelFactory = EditMealViewModelFactory(mealFoodEntryDao, mealDao, application, args.mealId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EditMealViewModel::class.java)


        val foodDao = AppDatabase.getInstance(application).foodDao()
        val adapter = MealFoodEntryAdapter(foodDao.getAll())
        binding.mealsRecyclerView.adapter = adapter

        viewModel.mealFoodEntries.observe(viewLifecycleOwner) { mealFoodEntries ->
            if (mealFoodEntries != null) {
                adapter.data = mealFoodEntries
            }
        }

        binding.buttonAddMealEntry.setOnClickListener {
            //создать entry, передать его дальше
            val mealEntryId = viewModel.saveMealFoodEntry(args.mealId, 1)
            val action = EditMealFragmentDirections.actionEditMealFragmentToAddMealFoodEntryFragment(mealEntryId)
            Navigation.findNavController(it).navigate(action)
        }
        binding.buttonDeleteMeal.setOnClickListener {
            viewModel.deleteMeal(args.mealId)
            Navigation.findNavController(it).navigateUp()
        }
        return binding.root
    }
}