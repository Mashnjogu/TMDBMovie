package com.example.tmdbmovie.presentation.FavoriteScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovie.data.FavoriteMovie
import com.example.tmdbmovie.data.local.FavoriteTv
import com.example.tmdbmovie.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavMovieViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel(){

    private val _favoriteMovies = MutableStateFlow(emptyList<FavoriteMovie>())
    val favoriteMovies get() = _favoriteMovies.asStateFlow()

    init {
        fetchFavMovies()
    }

    private fun fetchFavMovies(){
        viewModelScope.launch {
            repository.getFavMovies()
                .flowOn(Dispatchers.IO)
                .collect{
                    _favoriteMovies.value = it
                }
        }
    }
    fun addFaveMovie(movie: FavoriteMovie){
        viewModelScope.launch {
            repository.addFavMovie(movie = movie)
        }
    }

    fun deleteFavMovie(movie: FavoriteMovie){
        viewModelScope.launch {
            repository.deleteFavMovie(movie)
                fetchFavMovies()
        }
    }

}