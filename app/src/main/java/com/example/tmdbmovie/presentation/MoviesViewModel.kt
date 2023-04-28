package com.example.tmdbmovie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovie.data.model.movies.MovieDataDTO
import com.example.tmdbmovie.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel(){

    private val _uiStatePopular = MutableStateFlow<List<MovieDataDTO>?>(null)
    private val _uiStateTopRated = MutableStateFlow<List<MovieDataDTO>?>(null)
    private val _uiStateNowPlaying = MutableStateFlow<List<MovieDataDTO>?>(null)
    private val _uiStateUpcoming = MutableStateFlow<List<MovieDataDTO>?>(null)
    private val _uiStateTrending = MutableStateFlow<List<MovieDataDTO>?>(null)

    val uiStatePopular: StateFlow<List<MovieDataDTO>?> = _uiStatePopular.asStateFlow()
    val uiStateTopRated: StateFlow<List<MovieDataDTO>?> = _uiStateTopRated.asStateFlow()
    val uiStateNowPlaying: StateFlow<List<MovieDataDTO>?> = _uiStateNowPlaying.asStateFlow()
    val uiStateUpcoming: StateFlow<List<MovieDataDTO>?> = _uiStateNowPlaying.asStateFlow()
    val uiStateTrending: StateFlow<List<MovieDataDTO>?> = _uiStateTrending.asStateFlow()


    init {
        getPopularMovies()
        getTopRated()
        getNowPlayingMovies()
        getUpcoming()
        getTrendingMovies()
    }

    private fun getPopularMovies(){
        viewModelScope.launch{
            repository.getPopularMovies()
                .catch {
                    it.printStackTrace()
                }
                .flowOn(Dispatchers.IO)
                .collect{
                    val popularMovies = it.results
                    println("Popular Movies: $popularMovies")
//                    Log.d("MyTag", "Popular Movies: $popularMovies")
                    if (popularMovies.isNotEmpty()){
                        _uiStatePopular.emit(popularMovies)
                    }

                }
        }
    }

    private fun getTopRated(){
        viewModelScope.launch {

            repository.getTopRatedMovies()

                .flowOn(Dispatchers.IO)
                .catch {
                    it.printStackTrace()
                }
                .collect{
                    val topRated = it.results
                    _uiStateTopRated.emit(topRated)
                }
        }
    }

    private fun getNowPlayingMovies(){
        viewModelScope.launch {

            repository.getNowPlayingMovies()
                .flowOn(Dispatchers.IO)
                .catch {
                    it.printStackTrace()
                }
                .collect{
                    val latestMovies = it.results
                    _uiStateNowPlaying.emit(latestMovies)
                }
        }
    }

    private fun getUpcoming(){
        viewModelScope.launch {

            repository.getUpcomingMovies()
                .flowOn(Dispatchers.IO)
                .catch {
                    it.printStackTrace()
                }
                .collect{
                    val upcomingMovies = it.results
                    _uiStateUpcoming.emit(upcomingMovies)
                }
        }
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {

            repository.getTrendingMovies()
                .flowOn(Dispatchers.IO)
                .collect{
                    val trendingMovies = it.results
                    _uiStateTrending.emit(trendingMovies)
                }
        }
    }
}
