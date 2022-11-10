package com.bertvanhecke.cryptocurrencyapp.screens.coindetail

import androidx.lifecycle.ViewModel
import com.bertvanhecke.cryptocurrencyapp.models.Coin
import timber.log.Timber

class CoinDetailViewModel(coin: Coin): ViewModel(){
    var coin = coin

    init {
        Timber.i("$coin")
    }
}