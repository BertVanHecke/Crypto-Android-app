package com.bertvanhecke.cryptocurrencyapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bertvanhecke.cryptocurrencyapp.models.Coin

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCoin(coin: Coin)

    //Gets all coins to display in the favorite adapter.
    @Query("SELECT * FROM coins WHERE owner == :id")
    fun getAllCoins(id: Int): LiveData<List<Coin>>

    @Delete
    suspend fun deleteCoin(coin: Coin)

}