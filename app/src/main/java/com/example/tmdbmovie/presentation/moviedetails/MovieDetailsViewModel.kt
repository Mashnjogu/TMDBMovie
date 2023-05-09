package com.example.tmdbmovie.presentation.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovie.domain.model.MovieInfo
import com.example.tmdbmovie.domain.usecase.GetDetails
import com.example.tmdbmovie.extras.MediaType
import com.example.tmdbmovie.extras.Resource
import com.example.tmdbmovie.extras.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieDetailsViewModel @Inject constructor(
    private val getDetails: GetDetails
): ViewModel(){

    protected var id by Delegates.notNull<Int>()

    private val _uiState = MutableStateFlow(UIState.loadingState())
    val uiState get() = _uiState.asStateFlow()

    private val _details = MutableStateFlow(MovieInfo.empty)
    val details get() = _details.asStateFlow()

    fun setId(newId: Int){
        id = newId
    }

    private fun fetchMovieDetails(){
        viewModelScope.launch {
            getDetails(MediaType.MOVIE, id).collect{
                when(it){
                    is Resource.Success -> {
                        (it.data as MovieInfo).apply {
                            _details.value = this
                        }
                        _uiState.value = UIState.successState()
                    }
                    is Resource.Error -> {
                        _uiState.value = UIState.errorState(errorText = it.message)
                    }

                }
            }
        }
    }
}