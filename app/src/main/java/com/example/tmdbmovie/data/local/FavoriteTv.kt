package com.example.tmdbmovie.data.local


data class FavoriteTv(
    val id: Int,
    val numOfSeasons: Int,
    val firstAirDate: String?,
    val name: String,
    val backdropPath: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val date: Long
)

