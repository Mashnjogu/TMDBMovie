package com.example.tmdbmovie.data.remote

import com.squareup.moshi.Json

data class MoviesDTO(
    @field:Json(name = "page")
    var page: Int,
    @field:Json(name = "page")
    var results: MovieDataDTO,
    @field:Json(name = "total_pages")
    var totalPages: Int,
    @field:Json(name = "total_pages")
    var totalResults: Int
)