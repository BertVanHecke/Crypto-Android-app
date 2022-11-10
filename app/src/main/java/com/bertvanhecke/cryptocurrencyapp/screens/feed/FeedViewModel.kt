package com.bertvanhecke.cryptocurrencyapp.screens.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bertvanhecke.cryptocurrencyapp.models.Coin
import com.bertvanhecke.cryptocurrencyapp.models.MarketData
import com.bertvanhecke.cryptocurrencyapp.models.Metrics
import timber.log.Timber

class FeedViewModel(): ViewModel() {

    private val _navigateToCoinDetail = MutableLiveData<Coin?>()
    val navigateToCoinDetail
        get() = _navigateToCoinDetail

    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
    val coins = MutableLiveData<List<Coin>>()
        .default(
            listOf(
                Coin(Metrics("1", MarketData(1, 7184.79746667989), "Bitcoin", "bitcoin", "BTC")),
                Coin(Metrics("2", MarketData(1, 7184.79746667989), "Bitcoin", "bitcoin", "BTC")),
                Coin(Metrics("3", MarketData(1, 7184.79746667989), "Bitcoin", "bitcoin", "BTC")),
                Coin(Metrics("4", MarketData(1, 7184.79746667989), "Bitcoin", "bitcoin", "BTC")),
                Coin(Metrics("5", MarketData(1, 7184.79746667989), "Bitcoin", "bitcoin", "BTC"))
            ))



    init {
        Timber.i("FeedViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("FeedViewModel destroyed!")
    }

    fun onCoinClicked(coin: Coin){
        _navigateToCoinDetail.value = coin
    }

    fun onCoinDetailNavigated() {
        _navigateToCoinDetail.value = null
    }
}
