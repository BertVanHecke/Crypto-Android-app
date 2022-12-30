package com.bertvanhecke.cryptocurrencyapp.screens.register

import androidx.lifecycle.*
import com.bertvanhecke.cryptocurrencyapp.models.User
import com.bertvanhecke.cryptocurrencyapp.repository.UserRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class RegisterViewModel(val userRepository: UserRepository): ViewModel() {

    var user = MutableLiveData<User?>()

    var loginError = MutableLiveData<String?>()
    var errorPassword = MutableLiveData<String?>()
    var errorUserExists = MutableLiveData<String?>()

    var userName = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    var navigateBack = MutableLiveData<Boolean>()

    init {
        navigateBack.value = false
        userName.value=""
        password.value=""
    }


    fun btnRegisterClicked() {
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
            viewModelScope.launch {
                val u = userRepository.checkIfUserExists(userName.value!!)

                if(u == null){
                    Timber.i("Username not yet in use, proceed")
                    val id = userRepository.insertUser(User(null, userName.value!!, password.value!!))
                    user.value = userRepository.getCurrentUser(id)
                }else {
                    Timber.i("User already exists")
                    errorUserExists.value = "Username already exists."
                }
            }
        }
    }

}