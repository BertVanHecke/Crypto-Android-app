package com.bertvanhecke.cryptocurrencyapp.screens.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertvanhecke.cryptocurrencyapp.models.Coin
import com.bertvanhecke.cryptocurrencyapp.models.CoinResponse
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import com.bertvanhecke.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class FeedViewModel(val coinRepository: CoinRepository): ViewModel() {

    private val _navigateToCoinDetail = MutableLiveData<Coin?>()
    val coins: MutableLiveData<Resource<CoinResponse>> = MutableLiveData()

    init {
        getAssets()
    }

    fun getAssets() = viewModelScope.launch {
        coins.postValue(Resource.Loading())
        val response = coinRepository.getAssets()
        coins.postValue(handleCoinsResponse(response))
    }

    private fun handleCoinsResponse(response: Response<CoinResponse>): Resource<CoinResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    val navigateToCoinDetail
        get() = _navigateToCoinDetail


    fun onCoinClicked(coin: Coin){
        _navigateToCoinDetail.value = coin
    }

    fun onCoinDetailNavigated() {
        _navigateToCoinDetail.value = null
    }
}
