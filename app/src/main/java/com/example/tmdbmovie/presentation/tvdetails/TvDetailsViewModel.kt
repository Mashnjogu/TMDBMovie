package com.example.tmdbmovie.presentation.tvdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovie.data.model.movies.MovieDetailDTO
import com.example.tmdbmovie.data.model.tvshows.TvDetailDataDTO
import com.example.tmdbmovie.domain.repository.MovieRepository
import com.example.tmdbmovie.extras.MOVIE_DETAIL_ID_KEY
import com.example.tmdbmovie.extras.TV_SHOWDETAIL_ID_KEY
import com.example.tmdbmovie.extras.TvShowDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MovieRepository
)
    : ViewModel(){

    private val tvShowId: Int = checkNotNull(savedStateHandle[TV_SHOWDETAIL_ID_KEY])



    private val _tvDetailsUiState = MutableStateFlow<TvShowDetailsUiState>(TvShowDetailsUiState.Loading)
    val tvDetailsUiState get() = _tvDetailsUiState.asStateFlow()

    private val _tvShowDetail = MutableStateFlow<TvDetailDataDTO?>(null)
    val tvShowDetails: StateFlow<TvDetailDataDTO?> = _tvShowDetail.asStateFlow()

    init {
        fetchTvShowDetails()
        getShowDetail()
    }

    private fun getShowDetail(){
        println("The id is: $tvShowId")
        viewModelScope.launch {
            repository.getTvShowDetails(tvShowId)
                .catch {
                    it.printStackTrace()
                }
                .flowOn(Dispatchers.IO)
                .collect{
                    _tvShowDetail.emit(it)
                }
        }
    }

    private fun fetchTvShowDetails(){
        viewModelScope.launch {
            try {
                _tvDetailsUiState.value = TvShowDetailsUiState.Loading
                repository.getTvShowDetails(tvShowId)
                    .flowOn(Dispatchers.IO)
                    .collect{ showDetails ->
                        println("The tvShowDetails are: $showDetails")
                        _tvDetailsUiState.value = TvShowDetailsUiState.Success(showDetails)
                    }
            }catch (e: Exception){
                _tvDetailsUiState.value = TvShowDetailsUiState.Error(e.message ?: "Unknown error message")
            }
        }
    }
}