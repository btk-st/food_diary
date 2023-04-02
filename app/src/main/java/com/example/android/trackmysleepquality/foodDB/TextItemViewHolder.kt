package com.example.android.trackmysleepquality.foodDB

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView) {
    init {
        textView.textSize = 25f
    }
}