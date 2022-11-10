package com.bertvanhecke.cryptocurrencyapp.models

import java.io.Serializable

data class User(var id: Int, var userName: String, var password: String) : Serializable