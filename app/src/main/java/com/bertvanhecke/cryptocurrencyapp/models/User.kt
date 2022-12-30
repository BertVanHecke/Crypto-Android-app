package com.bertvanhecke.cryptocurrencyapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users", indices = [Index(value = ["username"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "username")
    var userName: String,
    @ColumnInfo(name = "password")
    var password: String
    ) : Serializable