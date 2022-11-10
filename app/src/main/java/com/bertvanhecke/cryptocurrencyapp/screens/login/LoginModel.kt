package com.bertvanhecke.cryptocurrencyapp.screens.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bertvanhecke.cryptocurrencyapp.models.User
import com.bertvanhecke.cryptocurrencyapp.utils.MockupUser

class LoginModel: ViewModel() {
    var user = MutableLiveData<User?>()
    var loginError = MutableLiveData<String?>()
    var errorPassword = MutableLiveData<String?>()

    var userName = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    private var mockupUser = MutableLiveData<MockupUser>()

    var navigateBack = MutableLiveData<Boolean>()

    init {
        mockupUser.value = MockupUser()
        mockupUser.value?.error?.observeForever(Observer {
            //it?.let {

            loginError.value = it
            //}
        })
        navigateBack.value = false
        userName.value=""
        password.value=""
    }

    fun btnLoginClicked() {
        if (userName.value.isNullOrBlank()) {
            loginError.value = "Gebruikersnaam mag niet leeg zijn."
        } else {
            loginError.value = null
        }
        if (password.value.isNullOrBlank()) {
            errorPassword.value = "Paswoord mag niet leeg zijn."

        } else  {
            errorPassword.value = null
        }

        if (errorPassword.value.isNullOrBlank() && loginError.value.isNullOrBlank()) {
            user.value = mockupUser.value?.getUser(userName.value, password.value)
        }
    }

    fun btnCancelClicked() {
        navigateBack.value = true
    }
}