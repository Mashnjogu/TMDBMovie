package com.example.tmdbmovie.domain.util

import com.example.tmdbmovie.data.remote.MoviesDTO

data class TMDBmovieState(
    val isLoading: Boolean = false,
    val movies: List<MoviesDTO> = emptyList(),
    val errorMessage: String? = null
)