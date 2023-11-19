package com.example.finsave.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finsave.R
import com.example.finsave.demo.entity.Spending

class SpendingListAdapter : ListAdapter<Spending, SpendingListAdapter.SpendingViewHolder>(SpendingsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendingViewHolder {
        return SpendingViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SpendingViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class SpendingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val spendingItemView: TextView = itemView.findViewById(R.id.spending_textview)

        fun bind(text: String?) {
            spendingItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): SpendingViewHolder{
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_category, parent, false)
                return SpendingViewHolder(view)

            }
        }
    }

    class SpendingsComparator : DiffUtil.ItemCallback<Spending>(){

        override fun areItemsTheSame(oldItem: Spending, newItem: Spending): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Spending, newItem: Spending): Boolean {
            return oldItem.name==newItem.name && oldItem.price==newItem.price
        }
    }

}

