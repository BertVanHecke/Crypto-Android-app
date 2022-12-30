package com.bertvanhecke.cryptocurrencyapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "coins")
data class Coin(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "slug")
    val slug: String,
    @ColumnInfo(name = "symbol")
    val symbol: String,
    @ColumnInfo(name = "metrics")
    val metrics: Metrics,
    @ColumnInfo(name = "owner")
    val owner: Int? = null
) : Serializable