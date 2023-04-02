package com.example.android.trackmysleepquality.addMealFoodEntry

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.AppDatabase
import com.example.android.trackmysleepquality.databinding.FragmentAddMealFoodEntryBinding

class AddMealFoodEntryFragment : Fragment() {
    private val args: AddMealFoodEntryFragmentArgs by navArgs()
    private lateinit var viewModel: AddMealFoodEntryViewModel


    private lateinit var binding: FragmentAddMealFoodEntryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddMealFoodEntryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this


        val application = requireNotNull(activity).application
        val dao = AppDatabase.getInstance(application).mealFoodEntryDao()
        val foodDao = AppDatabase.getInstance(application).foodDao()

        val viewModelFactory = AddMealFoodEntryViewModelFactory(dao, args.mealEntryId, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddMealFoodEntryViewModel::class.java)

        // Получаем список доступной еды
        val foodList = foodDao.getAll()
        foodList.observe(viewLifecycleOwner) { foods ->
            val foodNamesList = foods.map { it.name + ", " + it.servingSize + ", " + it.calories + " kcal"} ?: emptyList()
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, foodNamesList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.foodSpinner.adapter = adapter
            //проставляем данные в форму
            viewModel.mealFoodEntry.observe(viewLifecycleOwner) { mealFoodEntry ->
                val position = foodList.value?.find { it.id == mealFoodEntry.foodId}?.id ?: 0
                binding.foodSpinner.setSelection(position.toInt()-1)
                binding.quantity.setText(mealFoodEntry.quantity.toString())
            }
        }

        // Выбираем еду из списка
        binding.foodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val foodId = foodList.value?.get(position)?.id
                // Сохраняем foodId
                if (foodId != null) {
                    viewModel.mealFoodEntry.value?.foodId  = foodId
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        //обработка кнопок
        binding.saveButton.setOnClickListener {
            viewModel.saveMealFoodEntry(binding.quantity.text.toString().toDouble())
            Navigation.findNavController(it).navigateUp()
        }
        binding.deleteButton.setOnClickListener {
            viewModel.deleteMealEntry()
            Navigation.findNavController(it).navigateUp()
        }
        return binding.root
    }
}