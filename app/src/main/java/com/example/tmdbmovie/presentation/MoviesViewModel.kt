package com.example.tmdbmovie.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovie.data.remote.MovieDataDTO
import com.example.tmdbmovie.data.remote.MoviesDTO
import com.example.tmdbmovie.domain.repository.MovieRepository
import com.example.tmdbmovie.domain.util.TMDBmovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel(){

    private val _uiStatePopular = MutableStateFlow(listOf<MovieDataDTO>())
    private val _uiStateTopRated = MutableStateFlow(listOf<MovieDataDTO>())
    private val _uiStateLatest = MutableStateFlow(listOf<MovieDataDTO>())

    val uiStatePopular: StateFlow<List<MovieDataDTO>> = _uiStatePopular.asStateFlow()
    val uiStateTopRated: StateFlow<List<MovieDataDTO>> = _uiStatePopular.asStateFlow()
    val uiStateLatest: StateFlow<List<MovieDataDTO>> = _uiStatePopular.asStateFlow()

    init {
        getPopularMovies()
//        getTopRated()
//        getLatest()
    }

    fun getPopularMovies(){
        viewModelScope.launch{
            repository.getPopularMovies()
                .flowOn(Dispatchers.IO)
                .catch {
                    it.printStackTrace()
                }
                .collect{
                    val popularMovies = it.results
                    _uiStatePopular.emit(listOf(popularMovies))
                }
        }
    }

    fun getTopRated(){
        viewModelScope.launch {

            repository.getTopRatedMovies()
                .flowOn(Dispatchers.IO)
                .catch {
                    it.printStackTrace()
                }
                .collect{
                    val topRated = it.results
                    _uiStateTopRated.emit(listOf(topRated))
                }
        }
    }

    fun getLatest(){
        viewModelScope.launch {

            repository.getLatestMovies()
                .flowOn(Dispatchers.IO)
                .catch {
                    it.printStackTrace()
                }
                .collect{
                    val latestMovies = it.results
                    _uiStateLatest.emit(listOf(latestMovies))
                }
        }
    }
}
