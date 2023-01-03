package com.bertvanhecke.cryptocurrencyapp.screens.feed

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bertvanhecke.cryptocurrencyapp.R
import com.bertvanhecke.cryptocurrencyapp.models.Coin
import java.text.NumberFormat
import java.util.*


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
        val format = "${item.metrics.market_data.price_btc} ${item.symbol}"
        text = format
    }

}

@BindingAdapter("USDValue")
fun TextView.setCoinUSDValue(item: Coin?) {
    item?.let {
        val format = NumberFormat.getCurrencyInstance(Locale("en", "US"))
            .format(item.metrics.market_data.price_usd)
        text = format.toString()
    }
}

@BindingAdapter("volumeLast24Hours")
fun TextView.setVolumeLast24Hours(item: Coin?) {
    item?.let {
        text = item.metrics.market_data.volume_last_24_hours.toString()
    }
}

@BindingAdapter("realVolumeLast24Hours")
fun TextView.setRealVolumeLast24Hours(item: Coin?) {
    item?.let {
        text = item.metrics.market_data.real_volume_last_24_hours.toString()
    }
}

@BindingAdapter("percentChangeUSDLast1Hour")
fun TextView.setPercentChangeUSDLast1Hour(item: Coin?) {
    item?.let {
        text = if(item.metrics.market_data.percent_change_usd_last_1_hour == null){
            R.string.nodata.toString()
        } else {
            val format = NumberFormat.getPercentInstance().format(item.metrics.market_data.percent_change_usd_last_1_hour)
            format.toString()
        }
    }
}
@BindingAdapter("percentChangeBTCLast1Hour")
fun TextView.setPercentChangeBTCLast1Hour(item: Coin?) {
    item?.let {
        text = if(item.metrics.market_data.percent_change_btc_last_1_hour == null){
            R.string.nodata.toString()
        } else {
            val format = NumberFormat.getPercentInstance().format(item.metrics.market_data.percent_change_btc_last_1_hour)
            format.toString()

        }
    }
}

@BindingAdapter("percentChangeUSDLast24Hour")
fun TextView.setPercentChangeUSDLast24Hour(item: Coin?) {
    item?.let {
        text = if(item.metrics.market_data.percent_change_usd_last_24_hours == null){
            R.string.nodata.toString()
        } else {
            val format = NumberFormat.getPercentInstance().format(item.metrics.market_data.percent_change_usd_last_24_hours)
            format.toString()
        }
    }
}

@BindingAdapter("percentChangeBTCLast24Hour")
fun TextView.setPercentChangeBTCLast24Hour(item: Coin?) {
    item?.let {
        text = if(item.metrics.market_data.percent_change_btc_last_24_hours == null){
            R.string.nodata.toString()
        } else {
            val format = NumberFormat.getPercentInstance().format(item.metrics.market_data.percent_change_btc_last_24_hours)
            format.toString()
        }
    }
}

@BindingAdapter("open")
fun TextView.setOpen(item: Coin?) {
    item?.let {
        text = if(item.metrics.market_data.ohlcv_last_1_hour?.open == null){
            R.string.nodata.toString()
        } else {
            item.metrics.market_data.ohlcv_last_1_hour.open.toString()

        }
    }
}

@BindingAdapter("high")
fun TextView.setHigh(item: Coin?) {
    item?.let {
        text = if(item.metrics.market_data.ohlcv_last_1_hour?.high == null){
            R.string.nodata.toString()
        } else {
            item.metrics.market_data.ohlcv_last_1_hour.high.toString()

        }
    }
}

@BindingAdapter("low")
fun TextView.setLow(item: Coin?) {
    item?.let {
        text = if(item.metrics.market_data.ohlcv_last_1_hour?.low == null){
            R.string.nodata.toString()
        } else {
            item.metrics.market_data.ohlcv_last_1_hour.low.toString()

        }
    }
}

@BindingAdapter("close")
fun TextView.setClose(item: Coin?) {
    item?.let {
        text = if(item.metrics.market_data.ohlcv_last_1_hour?.close == null){
            R.string.nodata.toString()
        } else {
            item.metrics.market_data.ohlcv_last_1_hour.close.toString()

        }
    }
}

@BindingAdapter("volume")
fun TextView.setVolume(item: Coin?) {
    item?.let {
        text = if(item.metrics.market_data.ohlcv_last_1_hour?.volume == null){
            R.string.nodata.toString()
        } else {
            item.metrics.market_data.ohlcv_last_1_hour.volume.toString()

        }
    }
}

