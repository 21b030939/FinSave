package com.example.finsave.network

import com.example.finsave.demo.model.db.entity.StockItem
import retrofit2.Response
import retrofit2.http.GET

interface StockApiService {
    @GET("stocks/list")
    suspend fun getStockList(): Response<List<StockItem>>
}