package com.example.tmdbmovie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovie.data.model.tvshows.TvShowDataDTO
import com.example.tmdbmovie.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    private val _uiStatePopularShows = MutableStateFlow<List<TvShowDataDTO>?>(null)
    private val _uiStateTopRatedShows = MutableStateFlow<List<TvShowDataDTO>?>(null)
    private val _uiStateOnAirShows = MutableStateFlow<List<TvShowDataDTO>?>(null)
    private val _uiStateTrending = MutableStateFlow<List<TvShowDataDTO>?>(null)

    val uiStatePopularShows: StateFlow<List<TvShowDataDTO>?> = _uiStatePopularShows.asStateFlow()
    val uiStateTopRatedShows: StateFlow<List<TvShowDataDTO>?> = _uiStateTopRatedShows.asStateFlow()
    val uiStateOnAirShows: StateFlow<List<TvShowDataDTO>?> = _uiStateOnAirShows.asStateFlow()
    val uiStateTrending: StateFlow<List<TvShowDataDTO>?> = _uiStateTrending.asStateFlow()

    init {
        getPopularShows()
        getTopRatedShows()
        getOnAirShows()
        getTrendingShows()
    }

    private fun getPopularShows() {
        viewModelScope.launch {
            repository.getPopularTVShows()
                .catch {
                    it.printStackTrace()
                }
                .flowOn(Dispatchers.IO)
                .collect{
                    val popularShows = it.results
                    if (popularShows.isNotEmpty()){
                        _uiStatePopularShows.emit(popularShows)
                    }
                }
        }
    }

    private fun getTopRatedShows() {
        viewModelScope.launch {
            repository.getTopRatedTVShows()
                .catch {
                    it.printStackTrace()
                }
                .flowOn(Dispatchers.IO)
                .collect{
                    val topRatedShows = it.results
                    if (topRatedShows.isNotEmpty()){
                        _uiStateTopRatedShows.emit(topRatedShows)
                    }
                }
        }
    }

    private fun getOnAirShows() {
        viewModelScope.launch {
            repository.getOnAirShows()
                .catch {
                    it.printStackTrace()
                }
                .flowOn(Dispatchers.IO)
                .collect{
                    val onAirShows = it.results
                    if (onAirShows.isNotEmpty()){
                        _uiStateOnAirShows.emit(onAirShows)
                    }
                }
        }
    }

    private fun getTrendingShows() {
        viewModelScope.launch {
            repository.getTrendingShows()
                .catch {
                    it.printStackTrace()
                }
                .flowOn(Dispatchers.IO)
                .collect{
                    val trendingShows = it.results
                    if (trendingShows.isNotEmpty()){
                        _uiStateOnAirShows.emit(trendingShows)
                    }
                }
        }
    }

}