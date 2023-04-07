package com.example.tmdbmovie.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _uiStatePopular = MutableStateFlow(listOf<MoviesDTO>())
    private val _uiStateTopRated = MutableStateFlow(listOf<MoviesDTO>())
    private val _uiStateLatest = MutableStateFlow(listOf<MoviesDTO>())

    val uiStatePopular: StateFlow<List<MoviesDTO>> = _uiStatePopular.asStateFlow()
    val uiStateTopRated: StateFlow<List<MoviesDTO>> = _uiStatePopular.asStateFlow()
    val uiStateLatest: StateFlow<List<MoviesDTO>> = _uiStatePopular.asStateFlow()

    init {
//        getPopularMovies()
//        getTopRated()
//        getLatest()
    }

//    fun getPopularMovies(){
//        viewModelScope.launch{
//            repository.getPopularMovies()
//                .flowOn(Dispatchers.IO)
//                .catch {
//                    it.printStackTrace()
//                }
//                .collect{
//                    val popularMovies = it.results as List<MoviesDTO>
//                    _uiStatePopular.emit(popularMovies)
//                }
//        }
//    }
//
//    fun getTopRated(){
//        viewModelScope.launch {
//
//            repository.getTopRatedMovies()
//                .flowOn(Dispatchers.IO)
//                .catch {
//                    it.printStackTrace()
//                }
//                .collect{
//                    val topRated = it.results as List<MoviesDTO>
//                    _uiStateTopRated.emit(topRated)
//                }
//        }
//    }
//
//    fun getLatest(){
//        viewModelScope.launch {
//
//            repository.getLatestMovies()
//                .flowOn(Dispatchers.IO)
//                .catch {
//                    it.printStackTrace()
//                }
//                .collect{
//                    val latestMovies = it.results as List<MoviesDTO>
//                    _uiStateLatest.emit(latestMovies)
//                }
//        }
//    }
}
