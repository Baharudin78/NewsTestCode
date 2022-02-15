package com.baharudin.newstestcode.data.remote.model

data class NewsResponse (
        val articles: List<Article>,
        val status: String,
        val totalResults: Int
        )