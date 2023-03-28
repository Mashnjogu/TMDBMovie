package com.example.tmdbmovie.domain.movies

import com.squareup.moshi.Json

data class MovieData(
    val id: Int,
    val title: String,
    val backdrop_path: String,
    val genre_id: Int,
    val original_language: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double
)

