package com.bertvanhecke.cryptocurrencyapp.models

data class NewsResponse(
    val status: StatusResponse,
    val data: List<News>
)
