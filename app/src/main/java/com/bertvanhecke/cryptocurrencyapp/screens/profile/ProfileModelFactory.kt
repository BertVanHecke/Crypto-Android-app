package com.bertvanhecke.cryptocurrencyapp.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bertvanhecke.cryptocurrencyapp.models.User

class ProfileModelFactory(private val user: User): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileModel::class.java)) {
            return ProfileModel(user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}