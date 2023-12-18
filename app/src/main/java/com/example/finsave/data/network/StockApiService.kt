package com.example.finsave.data.network

import com.example.finsave.data.model.entity.StockItem
import retrofit2.Response
import retrofit2.http.GET

interface StockApiService {
    @GET("stock/list?apikey=kxLWI2uL6i8qHRChasyqNGOYXqBMpj4H")
    suspend fun getStockList(): Response<List<StockItem>>
}