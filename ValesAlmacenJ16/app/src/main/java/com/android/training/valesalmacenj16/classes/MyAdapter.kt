package com.android.training.valesalmacenj16.classes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.training.valesalmacenj16.R

internal class MyAdapter (private val context: Context, private val list: ArrayList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView : TextView

        init {
            textView = itemView.findViewById(R.id.textViewClaveLista) // Initialize your All views prensent in list items
        }

        fun bind(position: Int) {
            // This method will be called anytime a list item is created or update its data
            //Do your stuff here
            textView.text = list[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}