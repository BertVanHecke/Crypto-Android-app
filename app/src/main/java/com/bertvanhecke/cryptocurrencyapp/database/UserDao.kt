package com.bertvanhecke.cryptocurrencyapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bertvanhecke.cryptocurrencyapp.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User): Long

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM users WHERE id == :id")
    suspend fun getCurrentUser(id: Long): User

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    suspend fun checkUsernameAndPassword(username: String, password: String): User?

    @Query("SELECT * FROM users WHERE username =:username LIMIT 1")
    suspend fun checkIfUsernameExists(username: String): User?

    @Query("DELETE FROM users")
    suspend fun clear()
}