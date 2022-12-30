package com.bertvanhecke.cryptocurrencyapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bertvanhecke.cryptocurrencyapp.models.Coin
import com.bertvanhecke.cryptocurrencyapp.models.User

@Database(entities = [Coin::class, User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CryptoDatabase: RoomDatabase() {
    abstract fun getCoinDao(): CoinDao
    abstract fun getUserDao(): UserDao

    companion object{
        @Volatile
        private var instance: CryptoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CryptoDatabase::class.java,
                "crypto_database"
            ).build()
    }
}