package com.example.android.trackmysleepquality.editCalories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.databinding.FragmentEditCaloriesBinding

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