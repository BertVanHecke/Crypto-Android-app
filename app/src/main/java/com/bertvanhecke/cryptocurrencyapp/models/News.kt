package com.bertvanhecke.cryptocurrencyapp.models

data class News(
    val author: Author,
    val content: String,
    val id: String,
    val published_at: String,
    val reference_title: String,
    val references: List<Reference>,
    val tags: List<String>,
    val title: String,
    val url: String
)