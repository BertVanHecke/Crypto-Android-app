package com.bertvanhecke.cryptocurrencyapp.screens.coinnews

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertvanhecke.cryptocurrencyapp.models.NewsResponse
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import com.bertvanhecke.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class CoinNewsViewModel(val coinRepository: CoinRepository, symbol: String):ViewModel() {

    val coinNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    init {
        getAssetNews(symbol)
    }

    private fun getAssetNews(symbol: String) = viewModelScope.launch {
        coinNews.postValue(Resource.Loading())
        val response = coinRepository.getAssetNews(symbol)
        coinNews.postValue(handleCoinNewsResponse(response))
    }


    private fun handleCoinNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    fun sharePDFNews(link: String?): Intent{
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            if(link == null){
                putExtra(Intent.EXTRA_TEXT, "No PDF Available")
            } else {
                putExtra(Intent.EXTRA_TEXT, link)
            }
            type = "text/plain"
        }
        return shareIntent
    }

}