package com.bertvanhecke.cryptocurrencyapp.network

import com.bertvanhecke.cryptocurrencyapp.models.CoinResponse
import com.bertvanhecke.cryptocurrencyapp.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MessariApi {
    @GET("v2/assets")
    suspend fun getAssets(): Response<CoinResponse>

    @GET("v1/news/{symbol}")
    suspend fun getAssetNews(@Path(value = "symbol") symbol: String): Response<NewsResponse>
}