package com.bertvanhecke.cryptocurrencyapp.models

data class CoinResponse(
    val status: StatusResponse,
    val data: List<Coin>
)
