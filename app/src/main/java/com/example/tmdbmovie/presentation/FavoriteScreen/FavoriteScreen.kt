package com.example.tmdbmovie.presentation.FavoriteScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdbmovie.composables.FavoriteMovieItem
import com.example.tmdbmovie.composables.FavoriteTvItem
import com.example.tmdbmovie.data.mappers.toFavoriteMovieEntity
import com.example.tmdbmovie.data.mappers.toFavoriteTvEntity
import com.google.common.math.LinearTransformation.horizontal


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteScreen(
    isDarkTheme: Boolean = isSystemInDarkTheme()
){

    val favoriteTvViewModel = hiltViewModel<FavTvViewModel>()
    val favoriteMovieViewModel = hiltViewModel<FavMovieViewModel>()

    val favTvShowsState = favoriteTvViewModel.favoriteTvShows.collectAsState()
    val favShows = favTvShowsState.value

    val favMoviesState = favoriteMovieViewModel.favoriteMovies.collectAsState()
    val favMovies = favMoviesState.value

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favorite Films",
                        style = MaterialTheme.typography.h3,
                        color = if (isDarkTheme) Color.White else Color.Black
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                navigationIcon = {
                    IconButton(onClick = {  }) {
//                        Icon(imageVector = Icons.Rounded.Menu,
//                            contentDescription = "Call Navigation Drawer")
                    }
                },
            )
        }
    ){
        Box(modifier = Modifier.padding(it)){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ){

                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn{
                    items(favShows.sortedByDescending { it.date }){
                        val dismissState = rememberDismissState()

                        if (dismissState.isDismissed(DismissDirection.EndToStart)){
                            favoriteTvViewModel.deleteFavTv(it)
                        }

                        SwipeToDismiss(
                            state = dismissState,
                            modifier = Modifier.padding(vertical = Dp(1f)),
                            directions = setOf(
                                DismissDirection.EndToStart
                            ),
                            dismissThresholds = { direction ->
                                FractionalThreshold(if(direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                            },
                            background = {
                                val color by animateColorAsState(
                                    when(dismissState.targetValue){
                                        DismissValue.Default -> Color.White
                                        else -> Color.Red
                                    }
                                )
                                val alignment = Alignment.CenterEnd
                                val icon = Icons.Default.Delete
                                val scale by animateFloatAsState(
                                    if(dismissState.targetValue == DismissValue.Default) 0.5f else 1f
                                )

                                Box(
                                    Modifier
                                        .fillMaxSize()
                                        .background(color)
                                        .padding(horizontal = Dp(20f))
                                ){
                                    Icon(
                                        icon,
                                        contentDescription = "Delete Icon",
                                        modifier = Modifier.scale(scale)
                                    )
                                }
                            },
                            dismissContent = {
                                Card(
                                    elevation = animateDpAsState(
                                        if (dismissState.dismissDirection != null) 4.dp else 0.dp
                                    ).value,
                                    modifier = Modifier
                                        .fillMaxWidth()
//                                        .height(Dp(50f))
                                        .align(alignment = Alignment.CenterVertically)
                                ) {
                                    FavoriteTvItem(favoriteTv = it.toFavoriteTvEntity())
                                }
                            }
                        )

//                        FavoriteTvItem(favoriteTv = it.toFavoriteTvEntity())
                    }
                    items(favMovies.sortedByDescending { it.date }){

                        val dismissState = rememberDismissState()

                        if (dismissState.isDismissed(DismissDirection.EndToStart)){
                            favoriteMovieViewModel.deleteFavMovie(it)
                        }

                        SwipeToDismiss(
                            state = dismissState,
                            modifier = Modifier.padding(vertical = Dp(1f)),
                            directions = setOf(
                                DismissDirection.EndToStart
                            ),
                            dismissThresholds = { direction ->
                                FractionalThreshold(if(direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                            },
                            background = {
                                val color by animateColorAsState(
                                    when(dismissState.targetValue){
                                        DismissValue.Default -> Color.White
                                        else -> Color.Red
                                    }
                                )
                                val alignment = Alignment.CenterEnd
                                val icon = Icons.Default.Delete
                                val scale by animateFloatAsState(
                                    if(dismissState.targetValue == DismissValue.Default) 0.5f else 1f
                                )

                                Box(
                                    Modifier
                                        .fillMaxSize()
                                        .background(color)
                                        .padding(horizontal = Dp(20f))
                                ){
                                    Icon(
                                        icon,
                                        contentDescription = "Delete Icon",
                                        modifier = Modifier.scale(scale)
                                    )
                                }
                            },
                            dismissContent = {
                                Card(
                                    elevation = animateDpAsState(
                                        if (dismissState.dismissDirection != null) 4.dp else 0.dp
                                    ).value,
                                    modifier = Modifier
                                        .fillMaxWidth()
//                                        .height(Dp(50f))
                                        .align(alignment = Alignment.CenterVertically)
                                ) {
                                    FavoriteMovieItem(favoriteMovie = it.toFavoriteMovieEntity())
                                }
                            }
                        )
                    }
                }
            }
        }
    }



}