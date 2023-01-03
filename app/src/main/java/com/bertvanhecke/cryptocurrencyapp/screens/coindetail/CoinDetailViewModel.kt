package com.bertvanhecke.cryptocurrencyapp.screens.coindetail

import androidx.lifecycle.*
import com.bertvanhecke.cryptocurrencyapp.models.Coin
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import kotlinx.coroutines.launch

class CoinDetailViewModel(val coin: Coin, val coinRepository: CoinRepository) :
    ViewModel() {

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
}