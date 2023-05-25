package com.example.tmdbmovie.presentation.moviedetails

import androidx.lifecycle.*
import com.example.tmdbmovie.data.model.movies.MovieDetailDTO
import com.example.tmdbmovie.domain.repository.MovieRepository
import com.example.tmdbmovie.extras.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MovieRepository
): ViewModel(){

    private val movieId: Int = checkNotNull(savedStateHandle[MOVIE_DETAIL_ID_KEY])

    private val _movieDetailsUiState = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val movieDetailsUiState get() = _movieDetailsUiState.asStateFlow()

    private val _movieDetail = MutableStateFlow<MovieDetailDTO?>(null)
    val movieDetail: StateFlow<MovieDetailDTO?> = _movieDetail.asStateFlow()


    init {
       fetchMovieDetails()
        getMoviezz()
    }

    private fun getMoviezz(){
        viewModelScope.launch {
            repository.getMovieDetails(movieId)
                .catch {
                    it.printStackTrace()
                }
                .flowOn(Dispatchers.IO)
                .collect{
                    _movieDetail.emit(it)
                }
        }
    }

     private fun fetchMovieDetails(){
         viewModelScope.launch {
             try {
                 _movieDetailsUiState.value = MovieDetailsUiState.Loading
                 repository.getMovieDetails(movieId)
                     .flowOn(Dispatchers.IO)
                     .collect{movieDetails ->
                         println("The movieDetails aree: $movieDetails")
                         _movieDetailsUiState.value = MovieDetailsUiState.Success(movieDetails)
                     }
             }catch(e: Exception){
                 _movieDetailsUiState.value = MovieDetailsUiState.Error(e.message ?: "Unknown error message")
             }
         }
    }
}