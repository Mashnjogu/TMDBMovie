package com.example.tmdbmovie.presentation.SearchScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovie.data.model.search.MultiSearchResponse
import com.example.tmdbmovie.data.model.search.Search
import com.example.tmdbmovie.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel(){

    val multiSearchPage = 1
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchedFilms = MutableStateFlow<List<Search>>(emptyList())
    val searchedFilms: StateFlow<List<Search>> = _searchedFilms.asStateFlow()

    private var job: Job? = null

    init {
        onSearch()
    }
    fun onSearchTextChanged(text: String){
        _searchText.value = text
        job?.cancel()
        job = viewModelScope.launch {
            delay(500)
            onSearch()
        }
    }

     fun onSearch(){
        viewModelScope.launch {
            repository.getMultiSearch(_searchText.value, multiSearchPage)
                .collect{
                    val results = it.searches
                    _searchedFilms.emit(results)
                }
        }
    }


}

