package com.bertvanhecke.cryptocurrencyapp.screens.coindetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bertvanhecke.cryptocurrencyapp.models.Coin
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository

class CoinDetailViewModelFactory(private val coin: Coin, val coinRepository: CoinRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoinDetailViewModel::class.java)) {
            return CoinDetailViewModel(coin, coinRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}