package com.example.finsave.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finsave.R
import com.example.finsave.data.model.entity.StockItem

class StockListAdapter(private var stocks: List<StockItem>) :
    RecyclerView.Adapter<StockListAdapter.StockViewHolder>() {

    class StockViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvStockName: TextView = view.findViewById(R.id.stock_name)
        val tvStockPrice: TextView = view.findViewById(R.id.stock_price)
        val tvStockSymbol: TextView = view.findViewById(R.id.stock_symbol)
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
        holder.tvStockSymbol.text = stock.symbol
    }

    override fun getItemCount() = stocks.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateStocks(newStocks: List<StockItem>) {
        stocks = newStocks
        notifyDataSetChanged()
    }
}