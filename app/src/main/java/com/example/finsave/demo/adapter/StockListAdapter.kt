package com.example.finsave.demo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finsave.R
import com.example.finsave.demo.model.db.entity.StockItem

class StockListAdapter(private var stocks: List<StockItem>) :
    RecyclerView.Adapter<StockListAdapter.StockViewHolder>() {

    class StockViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvStockName: TextView = view.findViewById(R.id.stock_name)
        val tvStockPrice: TextView = view.findViewById(R.id.stock_price)
        val tvStockChange: TextView = view.findViewById(R.id.stock_price_change)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stocks, parent, false)
        return StockViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val stock = stocks[position]
        holder.tvStockName.text = stock.name
        holder.tvStockPrice.text = String.format("$%.2f", stock.price)
        holder.tvStockChange.text = String.format("%.2f%%", stock.percentageChange)

        // Optionally, you can change the text color based on positive or negative percentage
        val changeColor = if (stock.percentageChange >= 0)
            android.R.color.holo_green_dark
        else
            android.R.color.holo_red_dark
        holder.tvStockChange.setTextColor(holder.itemView.context.resources.getColor(changeColor))
    }

    override fun getItemCount() = stocks.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateStocks(newStocks: List<StockItem>) {
        stocks = newStocks
        notifyDataSetChanged()
    }
}