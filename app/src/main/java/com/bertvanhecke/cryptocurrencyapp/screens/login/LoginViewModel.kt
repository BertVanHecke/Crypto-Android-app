package com.bertvanhecke.cryptocurrencyapp.screens.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertvanhecke.cryptocurrencyapp.models.User
import com.bertvanhecke.cryptocurrencyapp.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {
    var user = MutableLiveData<User?>()
    var loginError = MutableLiveData<String?>()
    var errorPassword = MutableLiveData<String?>()
    var errorLogin = MutableLiveData<String?>()

    var userName = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private var navigateBack = MutableLiveData<Boolean>()

    init {
        navigateBack.value = false
        userName.value=""
        password.value=""
    }

    fun btnLoginClicked() {
        if (userName.value.isNullOrBlank()) {
            loginError.value = "Username can not be empty."
        } else {
            loginError.value = null
        }
        if (password.value.isNullOrBlank()) {
            errorPassword.value = "Password can not be empty."

        } else  {
            errorPassword.value = null
        }

        if (errorPassword.value.isNullOrBlank() && loginError.value.isNullOrBlank()) {
            viewModelScope.launch {
                val u = userRepository.checkUsernameAndPassword(userName.value!!, password.value!!)
                if(u == null){
                    errorLogin.value = "Username or password is not correct, try again."
                }else {
                    errorLogin.value = null
                    user.value = u
                }
            }
        }
    }

}