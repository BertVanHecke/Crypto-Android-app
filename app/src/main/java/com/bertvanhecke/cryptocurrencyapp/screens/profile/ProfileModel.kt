package com.bertvanhecke.cryptocurrencyapp.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bertvanhecke.cryptocurrencyapp.models.User

class ProfileModel(__user: User): ViewModel() {
    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() {
            return _user
        }

    init {
        _user.value = __user
    }

}