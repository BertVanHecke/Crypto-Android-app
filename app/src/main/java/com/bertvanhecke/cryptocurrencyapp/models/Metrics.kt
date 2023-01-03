package com.bertvanhecke.cryptocurrencyapp.models

import java.io.Serializable

data class Metrics(
    val market_data: MarketData,
) : Serializable