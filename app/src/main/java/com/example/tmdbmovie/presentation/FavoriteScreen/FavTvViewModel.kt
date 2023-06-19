package com.example.tmdbmovie.presentation.FavoriteScreen

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovie.data.local.FavoriteTv
import com.example.tmdbmovie.data.local.entity.FavoriteTvEntity
import com.example.tmdbmovie.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavTvViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    private val _favoriteTvShows = MutableStateFlow(emptyList<FavoriteTv>())
    val favoriteTvShows get() = _favoriteTvShows.asStateFlow()

    init {
        fetchFavTvShows()
    }

    private fun fetchFavTvShows(){
        viewModelScope.launch {
            repository.getFavTvShows()
                .flowOn(Dispatchers.IO)
                .collect{
                    _favoriteTvShows.value = it
                }
        }
    }
    fun addFaveTvShow(tv: FavoriteTv){
        viewModelScope.launch {
            repository.addFavTvShow(tvShow = tv)
        }
    }

}