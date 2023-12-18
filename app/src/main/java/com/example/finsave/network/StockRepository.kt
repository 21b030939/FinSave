package com.example.finsave.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.finsave.demo.model.db.entity.StockItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.await

class StockRepository(private val apiService: StockApiService) {
    suspend fun getStockList(): List<StockItem> {
        val response = apiService.getStockList()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw RuntimeException("API call failed: ${response.message()}")
        }
    }
}