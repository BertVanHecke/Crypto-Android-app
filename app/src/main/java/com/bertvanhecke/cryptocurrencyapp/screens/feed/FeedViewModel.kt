package com.bertvanhecke.cryptocurrencyapp.screens.feed

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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

    private fun getAssets() = viewModelScope.launch {
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

    fun internetCheck(c: Context): Boolean {
        val cmg = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Android 10+
            cmg.getNetworkCapabilities(cmg.activeNetwork)?.let { networkCapabilities ->
                return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
        } else {
            return cmg.activeNetworkInfo?.isConnectedOrConnecting == true
        }

        return false
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
