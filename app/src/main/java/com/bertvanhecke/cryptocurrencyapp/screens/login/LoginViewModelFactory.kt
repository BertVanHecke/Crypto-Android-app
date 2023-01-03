package com.bertvanhecke.cryptocurrencyapp.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bertvanhecke.cryptocurrencyapp.repository.UserRepository

class LoginViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}