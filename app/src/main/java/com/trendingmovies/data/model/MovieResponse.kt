package com.trendingmovies.data.model

data class MovieResponse(
    var page: String,
    var results: List<MovieItem>
)