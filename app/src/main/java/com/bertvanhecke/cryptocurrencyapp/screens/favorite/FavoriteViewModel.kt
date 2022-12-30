package com.bertvanhecke.cryptocurrencyapp.screens.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertvanhecke.cryptocurrencyapp.models.*
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(val coinRepository: CoinRepository) : ViewModel() {

    private val _navigateToCoinDetail = MutableLiveData<Coin?>()
    val navigateToCoinDetail
        get() = _navigateToCoinDetail

    fun saveCoin(coin: Coin, userId: Int) = viewModelScope.launch {
        coinRepository.upsertCoin(
            Coin(
                coin.id,
                coin.name,
                coin.slug,
                coin.symbol,
                coin.metrics,
                userId
            )
        )
    }

    fun getFavoriteCoins(id: Int) = coinRepository.getAllCoins(id)

    fun deleteCoin(coin: Coin) = viewModelScope.launch {
        coinRepository.deleteCoin(coin)
    }

    fun onCoinClicked(coin: Coin){
        _navigateToCoinDetail.value = coin
    }

    fun onCoinDetailNavigated() {
        _navigateToCoinDetail.value = null
    }
}