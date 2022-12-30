package com.bertvanhecke.cryptocurrencyapp.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Metrics(
    val market_data: MarketData,
) : Serializable