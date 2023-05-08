package com.example.tmdbmovie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MovieList(
    val results: List<Movie>,
    val totalResults: Int
){
    companion object{
        val empty = MovieList(
            results = emptyList(),
            totalResults = 0
        )
    }
}

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val original_language: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double
): Parcelable