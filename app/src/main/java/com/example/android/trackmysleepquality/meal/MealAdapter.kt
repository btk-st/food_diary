package com.example.android.trackmysleepquality.meal

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.MealWithCalories
import com.example.android.trackmysleepquality.foodDB.TextItemViewHolder
import java.text.SimpleDateFormat
import java.util.*

class MealAdapter(private val context: Context) : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<MealWithCalories>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        holder.textView.setTextSize(30f)
        val item = data[position]
        val date = Date(item.meal.date)
        val dateStr = when {
            DateUtils.isToday(date.time) -> context.getString(R.string.today)
            DateUtils.isToday(date.time + DateUtils.DAY_IN_MILLIS) -> context.getString(R.string.yesterday)
            DateUtils.isToday(date.time + DateUtils.DAY_IN_MILLIS * 2) -> context.getString(R.string.day_before_yesterday)
            else -> SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)
        }
        val timeStr = SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
        holder.itemView.tag = position
        holder.textView.text = "$dateStr ${context.getString(R.string.in_preposition)} $timeStr, ${item.calories} kcal"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView
        view.setOnClickListener {
            val position = it.tag as Int
            val meal = data[position]
            val action = FoodDiaryFragmentDirections.actionFoodDiaryFragmentToEditMealFragment(meal.meal.id)
            view.findNavController().navigate(action)
        }
        return TextItemViewHolder(view)
    }
}