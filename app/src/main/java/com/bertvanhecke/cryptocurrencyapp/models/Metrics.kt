package com.bertvanhecke.cryptocurrencyapp.models

import android.os.Parcel
import android.os.Parcelable

data class Metrics(
    val id: String?,
    val market_data: MarketData?,
    val name: String?,
    val slug: String?,
    val symbol: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(MarketData::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(market_data, flags)
        parcel.writeString(name)
        parcel.writeString(slug)
        parcel.writeString(symbol)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Metrics> {
        override fun createFromParcel(parcel: Parcel): Metrics {
            return Metrics(parcel)
        }

        override fun newArray(size: Int): Array<Metrics?> {
            return arrayOfNulls(size)
        }
    }
}