package com.bertvanhecke.cryptocurrencyapp.screens.coinnews

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertvanhecke.cryptocurrencyapp.constants.Constants
import com.bertvanhecke.cryptocurrencyapp.models.CoinResponse
import com.bertvanhecke.cryptocurrencyapp.models.News
import com.bertvanhecke.cryptocurrencyapp.models.NewsResponse
import com.bertvanhecke.cryptocurrencyapp.network.MessariApi
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import com.bertvanhecke.cryptocurrencyapp.utils.Resource
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

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