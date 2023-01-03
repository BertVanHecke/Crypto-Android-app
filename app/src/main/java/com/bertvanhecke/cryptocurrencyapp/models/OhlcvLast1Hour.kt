package com.bertvanhecke.cryptocurrencyapp.models

import java.io.Serializable

data class OhlcvLast1Hour(
    val open: Double?,
    val high: Double?,
    val low: Double?,
    val close: Double?,
    val volume: Double?
): Serializable
