package com.bertvanhecke.cryptocurrencyapp.database

import androidx.room.TypeConverter
import com.bertvanhecke.cryptocurrencyapp.models.MarketData
import com.bertvanhecke.cryptocurrencyapp.models.Metrics
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromMetricsToString(metrics: Metrics): String = Gson().toJson(metrics)

    @TypeConverter
    fun fromStringToMetrics(json: String): Metrics = Gson().fromJson(json, Metrics::class.java)

    @TypeConverter
    fun fromMarketDataToString(marketData: MarketData): String = Gson().toJson(marketData)

    @TypeConverter
    fun fromStringToMarketData(json: String): MarketData = Gson().fromJson(json, MarketData::class.java)
 }