package com.example.tmdbmovie.extras

import android.os.Parcelable
import com.example.tmdbmovie.data.model.movies.MovieDetailDTO
import com.example.tmdbmovie.data.model.tvshows.TvDetailDataDTO
import com.example.tmdbmovie.domain.model.MovieInfo
import kotlinx.parcelize.Parcelize

sealed class Resource<out T>{
    data class Success<out T>(val data: T): Resource<T>()
    data class Error(val message: String): Resource<Nothing>()
}

sealed class MovieDetailsUiState{
    object Loading: MovieDetailsUiState()
    data class Success(val movieDetails: MovieDetailDTO): MovieDetailsUiState()
    data class Error(val message: String): MovieDetailsUiState()
}
sealed class TvShowDetailsUiState{
    object Loading: TvShowDetailsUiState()
    data class Success(val showDetails: TvDetailDataDTO): TvShowDetailsUiState()
    data class Error(val message: String): TvShowDetailsUiState()
}




@Parcelize
enum class MediaType : Parcelable {
    MOVIE,TV, PERSON
}