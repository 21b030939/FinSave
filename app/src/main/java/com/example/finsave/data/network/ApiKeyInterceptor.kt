package com.example.finsave.data.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    private val apiKey = "list?apikey=kxLWI2uL6i8qHRChasyqNGOYXqBMpj4H"  // Your actual API key

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        // Add API key to the original URL
        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter("apikey", apiKey)
            .build()

        // Build a new request with the updated URL
        val newRequest = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(newRequest)
    }
}
