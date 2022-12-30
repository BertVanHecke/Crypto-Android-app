package com.bertvanhecke.cryptocurrencyapp.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import com.bertvanhecke.cryptocurrencyapp.screens.feed.FeedViewModel

class FavoriteViewModelFactory(val coinRepository: CoinRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(coinRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}