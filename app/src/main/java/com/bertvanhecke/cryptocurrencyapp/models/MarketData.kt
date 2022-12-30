package com.bertvanhecke.cryptocurrencyapp.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class MarketData(
    val price_btc: Double,
    val price_usd: Double
) : Serializable