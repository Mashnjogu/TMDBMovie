package com.example.tmdbmovie.data.model.movies

import com.google.gson.annotations.SerializedName

data class MoviesDTO(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: List<MovieDataDTO>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int

)