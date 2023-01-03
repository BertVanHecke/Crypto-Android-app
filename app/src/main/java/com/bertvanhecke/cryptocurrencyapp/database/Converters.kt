package com.bertvanhecke.cryptocurrencyapp.database

import androidx.room.TypeConverter
import com.bertvanhecke.cryptocurrencyapp.models.Metrics
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromMetricsToString(metrics: Metrics): String = Gson().toJson(metrics)

    @TypeConverter
    fun fromStringToMetrics(json: String): Metrics = Gson().fromJson(json, Metrics::class.java)
 }