package com.bertvanhecke.cryptocurrencyapp.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class MarketData(
    val price_btc: Double,
    val price_usd: Double,
    val volume_last_24_hours: Double,
    val real_volume_last_24_hours: Double,
    val percent_change_usd_last_1_hour: Double?,
    val percent_change_btc_last_1_hour: Double?,
    val percent_change_usd_last_24_hours: Double?,
    val percent_change_btc_last_24_hours: Double?,
    val ohlcv_last_1_hour: OhlcvLast1Hour?
) : Serializable