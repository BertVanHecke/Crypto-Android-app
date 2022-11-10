package com.bertvanhecke.cryptocurrencyapp.utils

import androidx.lifecycle.MutableLiveData
import com.bertvanhecke.cryptocurrencyapp.models.User

class MockupUser {

    private val user1 = User(2, "Test2","password")
    private val user2 = User(1, "Test1","password")
    private val users = arrayOf(user1, user2)
    var error = MutableLiveData<String?>()

    fun getUser(userName: String?, password: String?) : User? {
        var user : User? = null
        var filterUsers = users.filter {
            it.userName.lowercase().equals(userName?.lowercase()) &&  it.password.equals(password)
        }
        if (filterUsers.isEmpty()) {
            error.value = "Fout bij inloggen. Gebruikersnaam of paswoord niet correct."
        } else {
            error.value = null
            user = filterUsers[0]
        }
        return user
    }

}