package com.example.tmdbmovie.presentation.SearchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tmdbmovie.composables.SearchItem

@Composable
fun SearchScreen(
    navController: NavController
){

    val viewModel = hiltViewModel<SearchViewModel>()

    val searchText by viewModel.searchText.collectAsState()

    val searchedFilmsState = viewModel.searchedFilms.collectAsState()
    val searchedFilms = searchedFilmsState.value



    Column(verticalArrangement = Arrangement.Center) {

        TextField(
            value = searchText,
            onValueChange = { text ->
                viewModel.onSearchTextChanged(text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            label = { Text(text = "Search") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                viewModel.onSearch()
            })
        )


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding( top = 6.dp, bottom = 6.dp)
            ){
                items(searchedFilms){
                    SearchItem(searchItem = it, navController = navController)
                }
            }


    }
}