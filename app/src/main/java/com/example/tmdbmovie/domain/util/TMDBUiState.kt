package com.example.tmdbmovie.domain.util

import com.example.tmdbmovie.data.model.movies.MoviesDTO

data class TMDBmovieState(
    val isLoading: Boolean = false,
    val movies: List<MoviesDTO> = emptyList(),
    val errorMessage: String? = null
)

const val BASE_URL = "https://api.themoviedb.org/3/"
const val POSTERPATHURL = "https://image.tmdb.org/t/p/original/"
const val BACKDROPPATHURL = "https://image.tmdb.org/t/p/w500/"