package com.example.android.trackmysleepquality.editMeal

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.Food
import com.example.android.trackmysleepquality.database.FoodDao
import com.example.android.trackmysleepquality.database.MealFoodEntry
import com.example.android.trackmysleepquality.foodDB.TextItemViewHolder

class MealFoodEntryAdapter(private val foodListLiveData: LiveData<List<Food>>) : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<MealFoodEntry>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        foodListLiveData.observe(holder.itemView.context as LifecycleOwner, Observer { foodList ->
            val food = foodList.firstOrNull { it.id == item.foodId }
            if (food != null) {
                holder.textView.text = "${food?.name}, ${item.quantity} * ${food?.servingSize}\n${food.calories * item.quantity} kcal"
            }
        })
        holder.itemView.tag = position
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView
        view.setOnClickListener {
            val position = it.tag as Int
            val mealFoodEntry = data[position]
            val action = EditMealFragmentDirections.actionEditMealFragmentToAddMealFoodEntryFragment(mealFoodEntry.id)
            view.findNavController().navigate(action)
        }
        return TextItemViewHolder(view)
    }
}

