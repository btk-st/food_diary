package com.example.android.trackmysleepquality.addFood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.database.AppDatabase
import com.example.android.trackmysleepquality.databinding.FragmentAddFoodBinding

class AddFoodFragment : Fragment() {
    private lateinit var binding: FragmentAddFoodBinding
    private lateinit var viewModel: AddFoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddFoodBinding.inflate(
            inflater,
            container,
            false
        )
        val application = requireNotNull(activity).application
        val dao = AppDatabase.getInstance(application).foodDao()

        val viewModelFactory = AddFoodViewModelFactory(dao, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(AddFoodViewModel::class.java)

        binding.buttonAddFood.setOnClickListener {
            val foodName = binding.editFoodName.text.toString()
            val calories = binding.editFoodCalories.text.toString()
            val servingSize = binding.editFoodQuantity.text.toString()
            if (calories.isBlank() || !calories.matches(Regex("^[0-9]+$"))) {
                Toast.makeText(requireContext(), "Invalid input for calories", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            viewModel.addFood(foodName, calories.toInt(), servingSize)
            this.findNavController().navigateUp()
        }


        return binding.root
    }
}