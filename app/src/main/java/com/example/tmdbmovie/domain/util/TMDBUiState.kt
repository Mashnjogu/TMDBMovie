package com.example.tmdbmovie.domain.util

import androidx.compose.ui.unit.dp
import com.example.tmdbmovie.data.model.movies.MoviesDTO

data class TMDBmovieState(
    val isLoading: Boolean = false,
    val movies: List<MoviesDTO> = emptyList(),
    val errorMessage: String? = null
)

const val BASE_URL = "https://api.themoviedb.org/3/"
const val POSTERPATHURL = "https://image.tmdb.org/t/p/original/"
const val BACKDROPPATHURL = "https://image.tmdb.org/t/p/w500/"

//collapsing and expanded toolbar heights
val COLLAPSED_TOP_BAR_HEIGHT = 56.dp
val EXPANDED_TOP_BAR_HEIGHT = 200.dp

val dummyImage = "https://images.unsplash.com/photo-1683009680116-b5c04463551d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80"