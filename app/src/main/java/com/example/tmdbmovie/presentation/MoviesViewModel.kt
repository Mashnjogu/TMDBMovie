package com.example.tmdbmovie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovie.data.remote.MoviesDTO
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

    private val _uiStatePopular = MutableStateFlow(listOf<MoviesDTO>())
    private val _uiStateTopRated = MutableStateFlow(listOf<MoviesDTO>())
    private val _uiStateLatest = MutableStateFlow(listOf<MoviesDTO>())

    val uiStatePopular: StateFlow<List<MoviesDTO>> = _uiStatePopular.asStateFlow()
    val uiStateTopRated: StateFlow<List<MoviesDTO>> = _uiStatePopular.asStateFlow()
    val uiStateLatest: StateFlow<List<MoviesDTO>> = _uiStatePopular.asStateFlow()

    init {

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
                    _uiStatePopular
                }
        }
    }

    fun getTopRated(){
        viewModelScope.launch {

        }
    }

    fun getLatest(){
        viewModelScope.launch {

        }
    }
}
