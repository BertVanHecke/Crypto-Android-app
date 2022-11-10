package com.bertvanhecke.cryptocurrencyapp.models

data class Metrics(
    val id: String,
    val market_data: MarketData,
    val name: String,
    val slug: String,
    val symbol: String
)