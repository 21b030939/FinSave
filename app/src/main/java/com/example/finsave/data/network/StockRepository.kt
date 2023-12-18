package com.example.finsave.data.network

import android.util.Log
import com.example.finsave.data.model.entity.StockItem
import retrofit2.await

class StockRepository(private val apiService: StockApiService) {

    suspend fun getStockList(): List<StockItem> {
        val response = apiService.getStockList()
        if (response.isSuccessful) {
            Log.d("StockRepository", "Response: ${response.body()}")
            return response.body() ?: emptyList()
        } else {
            Log.e("StockRepository", "Error: ${response.errorBody()?.string()}")
            throw RuntimeException("API call failed: ${response.message()}")
        }
    }
}
