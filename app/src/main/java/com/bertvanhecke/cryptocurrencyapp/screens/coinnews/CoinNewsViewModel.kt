package com.bertvanhecke.cryptocurrencyapp.screens.coinnews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bertvanhecke.cryptocurrencyapp.models.Author
import com.bertvanhecke.cryptocurrencyapp.models.News
import com.bertvanhecke.cryptocurrencyapp.models.Reference

class CoinNewsViewModel(symbol: String):ViewModel() {

    //symbol for api call
    var symbol = symbol

    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
    val news = MutableLiveData<News>().default(
        News(
            Author("Eric Turner"),
            "A report from KPMG with Coinbase, Fundstrat Global Advisors and Morgan Creek Digital identifies the underlying value, growth potential, and current barriers for crypto assets like Bitcoin and Ethereum.\n A report from KPMG with Coinbase, Fundstrat Global Advisors and Morgan Creek Digital identifies the underlying value, growth potential, and current barriers for crypto assets like Bitcoin and Ethereum.\n A report from KPMG with Coinbase, Fundstrat Global Advisors and Morgan Creek Digital identifies the underlying value, growth potential, and current barriers for crypto assets like Bitcoin and Ethereum.\n",
            "ee9da78b-b500-4be1-88c3-9d412cfe9f48",
            "2018-11-19T21:00:00Z",
            "Crypto assets are impossible to ignore KPMG",
            listOf(
                Reference(
                    "KPMG",
                    "https://assets.kpmg.com/content/dam/kpmg/us/pdf/2018/11/institutionalization-cryptoassets.pdf"
                )
            ),
            listOf("btc"),
            "[Analysis] Crypto assets are impossible to ignore KPMG",
            "https://image.cnbcfm.com/api/v1/image/106911087-16263029762021-07-12t182451z_1049474836_rc26jo9ua1af_rtrmadp_0_fintech-crypto-flows.jpeg?v=1626462384&w=740&h=416&ffmt=webp"
        )
    )

}