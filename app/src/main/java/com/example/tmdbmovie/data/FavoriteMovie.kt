package com.example.tmdbmovie.data

data class FavoriteMovie(
    val id: Int,
    val backdropPath:String?,
    val releaseDate: String?,
    val runtime: Int?,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val date: Long
)

