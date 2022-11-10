package com.bertvanhecke.cryptocurrencyapp

import com.bertvanhecke.cryptocurrencyapp.models.User

class UserSingelton {
    var user : User? = null

    init {
        instance = this
    }

    companion object {
        private var instance: UserSingelton? = null

        fun instance() : UserSingelton {
            if (instance == null) {
                instance = UserSingelton()
            }
            return instance as UserSingelton
        }
    }
}