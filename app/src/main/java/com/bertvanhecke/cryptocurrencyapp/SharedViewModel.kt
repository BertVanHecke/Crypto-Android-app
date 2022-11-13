package com.bertvanhecke.cryptocurrencyapp

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bertvanhecke.cryptocurrencyapp.models.Coin
import timber.log.Timber

class SharedViewModel: ViewModel() {
    private var _favorites = MutableLiveData<List<Coin>>()
    var favorites: LiveData<List<Coin>> = _favorites

    fun addFavoriteCoin(coin: Coin){
        Timber.i("In function")
        //TODO Add functie werkt niet met mutablelist, wel met normale list.
        favorites.value?.toMutableList()?.add(coin)
        Timber.i(favorites.value.toString())
        Timber.i(_favorites.value.toString())
    }

    fun removeFavoriteCoin(coin: Coin){
        //TODO Remove functie werkt niet met mutablelist, wel met normale list.
        favorites.value?.toMutableList()?.remove(coin)
    }
}