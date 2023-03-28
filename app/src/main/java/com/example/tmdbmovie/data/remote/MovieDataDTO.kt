package com.example.tmdbmovie.data.remote

import com.squareup.moshi.Json

data class MovieDataDTO(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "backdrop_path")
    val backdrop_path: String,
    @field:Json(name = "genre_ids")
    val genre_ids: List<Int>,
    @field:Json(name = "original_language")
    val original_language: String,
    @field:Json(name = "overview")
    val overview: String,
    @field:Json(name = "poster_path")
    val poster_path: String,
    @field:Json(name = "release_date")
    val release_date: String,
    @field:Json(name = "vote_average")
    val vote_average: Double
)
