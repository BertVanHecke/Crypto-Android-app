package com.bertvanhecke.cryptocurrencyapp.models

import com.bertvanhecke.cryptocurrencyapp.models.News
import com.bertvanhecke.cryptocurrencyapp.models.StatusResponse

data class NewsResponse(
    val status: StatusResponse,
    val data: List<News>
)
