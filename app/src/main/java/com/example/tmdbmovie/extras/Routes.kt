package com.example.tmdbmovie.extras

sealed class Routes(val route: String){
    object HomeScreen: Routes("Homescreen")
    object MovieDetail: Routes("MovieDetail")
    object ShowDetail: Routes("ShowDetail")
}