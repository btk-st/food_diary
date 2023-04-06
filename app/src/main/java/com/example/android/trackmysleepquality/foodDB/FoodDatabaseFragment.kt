package com.example.android.trackmysleepquality.foodDB

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.AppDatabase
import com.example.android.trackmysleepquality.databinding.FragmentFoodDatabaseBinding

class FoodDatabaseFragment : Fragment() {

    private lateinit var binding: FragmentFoodDatabaseBinding
    private lateinit var viewModel: FoodDatabaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("FoodDatabaseFragment", "Called FoodDatabaseViewModel.get")

        binding = FragmentFoodDatabaseBinding.inflate(inflater, container, false)

        val application = requireNotNull(activity).application
        val dao = AppDatabase.getInstance(application).foodDao()

        val viewModelFactory = FoodDatabaseViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FoodDatabaseViewModel::class.java)


        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.btnAddFood.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_foodDatabaseFragment_to_addFoodFragment)
        }

        binding.btnToDiary.setOnClickListener {
            val action = FoodDatabaseFragmentDirections.actionFoodDatabaseFragmentToFoodDiaryFragment()
            Navigation.findNavController(it).navigate(action)
        }

        val adapter = FoodAdapter()
        binding.foodList.adapter = adapter
        viewModel.foods.observe(viewLifecycleOwner) { foods ->
            if (foods != null) {
                adapter.data = foods
            }
        }

        return binding.root
    }

}
