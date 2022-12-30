package com.bertvanhecke.cryptocurrencyapp.repository

import com.bertvanhecke.cryptocurrencyapp.database.CryptoDatabase
import com.bertvanhecke.cryptocurrencyapp.models.Coin
import com.bertvanhecke.cryptocurrencyapp.network.RetrofitInstance

class CoinRepository(val db: CryptoDatabase) {

    suspend fun getAssets() = RetrofitInstance.api.getAsssets()

    suspend fun getAssetNews(symbol: String) = RetrofitInstance.api.getAssetNews(symbol)

    suspend fun upsertCoin(coin: Coin) = db.getCoinDao().upsertCoin(coin)

    fun getAllCoins(id: Int) = db.getCoinDao().getAllCoins(id)

    suspend fun deleteCoin(coin: Coin) = db.getCoinDao().deleteCoin(coin)

}