package com.example.tmdbmovie.data.model.tvshows

import com.google.gson.annotations.SerializedName

data class TvShowDataDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("first_air_date")
    val first_air_date: String,
    @SerializedName("genre_ids")
    val genre_ids: List<Int>,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
)
