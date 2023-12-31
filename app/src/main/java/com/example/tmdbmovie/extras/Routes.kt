package com.example.tmdbmovie.extras

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val route: String){
    object HomeScreen: Routes("Homescreen")
    object MovieDetail: Routes("MovieDetail/${Args.movieId}"){
        object Args{
            const val movieId = "movieId"
        }
    }
    object ShowDetail: Routes("ShowDetail/${Args.tvShowId}"){
        object Args{
            const val tvShowId = "tvShowId"
        }
    }
}

sealed class BottomNavigationScreens(
    val route: String,
    val icon: ImageVector
){
    object Home: BottomNavigationScreens(
        route = "Home",
        icon = Icons.Default.Home
    )
    object Search: BottomNavigationScreens(
        route = "Search",
        icon = Icons.Default.Search
    )
    object Favorite: BottomNavigationScreens(
        route = "Favorite",
        icon = Icons.Default.Favorite
    )
}

const val MOVIE_DETAIL_ID_KEY = "movieId"
const val TV_SHOWDETAIL_ID_KEY = "tvShowId"