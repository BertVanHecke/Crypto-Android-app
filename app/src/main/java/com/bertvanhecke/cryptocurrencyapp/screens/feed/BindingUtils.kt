package com.bertvanhecke.cryptocurrencyapp.screens.feed

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bertvanhecke.cryptocurrencyapp.models.Coin
import java.text.NumberFormat
import java.util.*


@BindingAdapter("id")
fun TextView.setCoinID(item: Coin?) {
    item?.let {
        text = item.id
    }
}

@BindingAdapter("name")
fun TextView.setCoinName(item: Coin?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("symbol")
fun TextView.setCoinSymbol(item: Coin?) {
    item?.let {
        text = item.symbol
    }
}

@BindingAdapter("absValue")
fun TextView.setCoinAbsoluteValue(item: Coin?) {
    item?.let {
        val format = item.metrics?.market_data?.price_btc.toString() + item.symbol
        text = format
    }

}

@BindingAdapter("USDValue")
fun TextView.setCoinUSDValue(item: Coin?) {
    item?.let {
        val format = NumberFormat.getCurrencyInstance(Locale("en", "US"))
            .format(item.metrics?.market_data?.price_usd)
        text = format.toString()
    }
}