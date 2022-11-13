package com.bertvanhecke.cryptocurrencyapp.models

import android.os.Parcel
import android.os.Parcelable

data class Coin(val metrics: Metrics?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Metrics::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(metrics, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coin> {
        override fun createFromParcel(parcel: Parcel): Coin {
            return Coin(parcel)
        }

        override fun newArray(size: Int): Array<Coin?> {
            return arrayOfNulls(size)
        }
    }
}