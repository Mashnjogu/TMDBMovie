package com.example.tmdbmovie.extras

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val route: String){
    object HomeScreen: Routes("Homescreen")
    object MovieDetail: Routes("MovieDetail")
    object ShowDetail: Routes("ShowDetail")
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