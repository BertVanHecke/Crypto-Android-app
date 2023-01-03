package com.bertvanhecke.cryptocurrencyapp.screens.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository

class FeedViewModelFactory(val coinRepository: CoinRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            return FeedViewModel(coinRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}