package com.example.finsave.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.finsave.demo.model.db.entity.StockItem
import com.example.finsave.network.StockRepository
import kotlinx.coroutines.launch

class StockViewModel(private val repository: StockRepository) : ViewModel() {

    private val _stockList = MutableLiveData<List<StockItem>>()
    val stockList: LiveData<List<StockItem>> = _stockList

    fun loadStockList() {
        viewModelScope.launch {
            try {
                val stocks = repository.getStockList()
                _stockList.postValue(stocks)
            } catch (e: Exception) {
                // Handle exceptions, e.g., by posting a value to an error LiveData
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
