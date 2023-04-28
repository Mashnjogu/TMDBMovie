package com.example.tmdbmovie.data.model.tvshows

import com.example.tmdbmovie.data.model.movies.MovieDataDTO
import com.google.gson.annotations.SerializedName

data class TvShowDTO(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: List<TvShowDataDTO>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
)