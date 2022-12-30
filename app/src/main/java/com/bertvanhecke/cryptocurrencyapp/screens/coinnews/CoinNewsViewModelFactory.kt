package com.bertvanhecke.cryptocurrencyapp.screens.coinnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import com.bertvanhecke.cryptocurrencyapp.screens.coindetail.CoinDetailViewModel

class CoinNewsViewModelFactory(val coinRepository: CoinRepository, private val symbol: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoinNewsViewModel::class.java)) {
            return CoinNewsViewModel(coinRepository,symbol) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}