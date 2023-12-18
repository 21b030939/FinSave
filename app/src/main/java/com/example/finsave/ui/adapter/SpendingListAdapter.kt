package com.example.finsave.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finsave.R
import com.example.finsave.util.SpendingListDiffUtilCallback
import com.example.finsave.data.model.entity.Spending

class SpendingListAdapter : ListAdapter<Spending, SpendingListAdapter.SpendingViewHolder>(
    SpendingListDiffUtilCallback()
) {

    var onToDoItemRemoved: ((Spending) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendingViewHolder {
        return SpendingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_spending, parent, false))
    }

    override fun onBindViewHolder(holder: SpendingViewHolder, position: Int) {
        val current = getItem(position)
        holder.apply {
            holder.spendingName.text = current.name
            holder.spendingCategory.text = current.category
            holder.spendingCost.text = current.price
            holder.spendingValue.text = current.value

        }
    }

    class SpendingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val spendingName: TextView = itemView.findViewById(R.id.spending_textview_name)
        val spendingCategory: TextView = itemView.findViewById(R.id.spending_textview_category)
        val spendingCost: TextView = itemView.findViewById(R.id.spending_textview_cost)
        val spendingValue: TextView = itemView.findViewById(R.id.spending_textview_value)
    }

}

