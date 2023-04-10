package com.example.tmdbmovie.presentation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController){

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                 TopAppBar(
                     title = {
                         Text(text = "MovieOnline")
                     },
                     backgroundColor = MaterialTheme.colors.background,
                     navigationIcon = {
                         IconButton(onClick = {  }) {
                             Icon(imageVector = Icons.Rounded.Menu,
                                 contentDescription = "Call Navigation Drawer")
                         }
                     },
                     actions = {
                                     IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Account")

            }
                     }
                 )
        },
        content = { paddingValues ->
            Home(paddingValues = paddingValues, scrollState = scrollState)
        }
    )
}

@Composable
fun Home(
    paddingValues: PaddingValues,
    scrollState: ScrollState
){
    Column {
        Text(text = "Movies and Shows")
    }
}