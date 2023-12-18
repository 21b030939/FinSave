package com.example.finsave.util

import androidx.recyclerview.widget.DiffUtil
import com.example.finsave.data.model.entity.Spending

class SpendingListDiffUtilCallback: DiffUtil.ItemCallback<Spending>(){

    override fun areItemsTheSame(oldItem: Spending, newItem: Spending): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Spending, newItem: Spending): Boolean {
        return oldItem.name==newItem.name && oldItem.price==newItem.price
    }

}