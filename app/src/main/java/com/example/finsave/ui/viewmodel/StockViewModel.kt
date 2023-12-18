package com.example.finsave.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.finsave.data.model.entity.StockItem
import com.example.finsave.data.network.StockRepository
import kotlinx.coroutines.launch

class StockViewModel(private val repository: StockRepository) : ViewModel() {

    private val _stockList = MutableLiveData<List<StockItem>>()
    val stockList: LiveData<List<StockItem>> = _stockList

    fun loadStockList() {
        viewModelScope.launch {
            try {
                val stocks = repository.getStockList()
                _stockList.postValue(stocks)
                Log.d("StockViewModel", "Stocks loaded: $stocks")
            } catch (e: Exception) {
                Log.e("StockViewModel", "Error: ${e.message}")
                // Handle exceptions
            }
        }
    }
}


class StockViewModelFactory(private val repository: StockRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StockViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
