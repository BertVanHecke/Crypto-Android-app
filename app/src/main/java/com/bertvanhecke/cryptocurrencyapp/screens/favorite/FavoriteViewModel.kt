package com.bertvanhecke.cryptocurrencyapp.screens.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bertvanhecke.cryptocurrencyapp.models.*

class FavoriteViewModel() : ViewModel() {

    private val _navigateToCoinDetail = MutableLiveData<Coin?>()
    val navigateToCoinDetail
        get() = _navigateToCoinDetail

    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
    val favorites = MutableLiveData<List<Coin>>()
        .default(
            listOf(
                Coin(Metrics("1", MarketData(1, 7184.79746667989), "Bitcoin", "bitcoin", "BTC")),
                Coin(Metrics("2", MarketData(1, 7184.79746667989), "Bitcoin", "bitcoin", "BTC")),
            ))

    fun onCoinClicked(coin: Coin){
        _navigateToCoinDetail.value = coin
    }

    fun onCoinDetailNavigated() {
        _navigateToCoinDetail.value = null
    }
}