package com.bertvanhecke.cryptocurrencyapp.repository

import com.bertvanhecke.cryptocurrencyapp.database.CryptoDatabase
import com.bertvanhecke.cryptocurrencyapp.models.User

class UserRepository(private val db: CryptoDatabase) {

    suspend fun insertUser(user: User): Long = db.getUserDao().insertUser(user)

    suspend fun getCurrentUser(id: Long): User = db.getUserDao().getCurrentUser(id)

    suspend fun checkUsernameAndPassword(username: String, password: String): User? = db.getUserDao().checkUsernameAndPassword(username, password)

    suspend fun checkIfUserExists(username: String): User? = db.getUserDao().checkIfUsernameExists(username)
}