package com.bertvanhecke.cryptocurrencyapp.screens.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bertvanhecke.cryptocurrencyapp.models.*

class FavoriteViewModel() : ViewModel() {

    private val _navigateToCoinDetail = MutableLiveData<Coin?>()
    val navigateToCoinDetail
        get() = _navigateToCoinDetail


    fun onCoinClicked(coin: Coin){
        _navigateToCoinDetail.value = coin
    }

    fun onCoinDetailNavigated() {
        _navigateToCoinDetail.value = null
    }
}