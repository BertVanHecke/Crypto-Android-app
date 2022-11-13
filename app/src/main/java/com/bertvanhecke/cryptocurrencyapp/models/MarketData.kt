package com.bertvanhecke.cryptocurrencyapp.models

import android.os.Parcel
import android.os.Parcelable

data class MarketData(
    val price_btc: Int,
    val price_usd: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(price_btc)
        parcel.writeDouble(price_usd)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MarketData> {
        override fun createFromParcel(parcel: Parcel): MarketData {
            return MarketData(parcel)
        }

        override fun newArray(size: Int): Array<MarketData?> {
            return arrayOfNulls(size)
        }
    }
}