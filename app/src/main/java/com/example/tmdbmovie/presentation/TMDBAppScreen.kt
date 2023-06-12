package com.example.tmdbmovie.presentation

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tmdbmovie.extras.BottomNavigationScreens
import com.example.tmdbmovie.extras.MOVIE_DETAIL_ID_KEY
import com.example.tmdbmovie.extras.Routes
import com.example.tmdbmovie.extras.TMDBAppState
import com.example.tmdbmovie.extras.TV_SHOWDETAIL_ID_KEY
import com.example.tmdbmovie.presentation.FavoriteScreen.FavoriteScreen
import com.example.tmdbmovie.presentation.SearchScreen.SearchScreen
import com.example.tmdbmovie.presentation.moviedetails.MovieDetailScreen
import com.example.tmdbmovie.presentation.tvdetails.TvDetailScreen
import com.example.tmdbmovie.utils.snackbar.SnackBarManager
import kotlinx.coroutines.CoroutineScope

@Composable
fun TMDBAppScreen(){

    val appState = rememebrAppState()
    val navController = rememberNavController()
    Scaffold(

        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.padding(8.dp),
                snackbar = { snackbarData ->
                    Snackbar(snackbarData = snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                }
            )
        },
        scaffoldState = appState.scaffoldState,

    ) { innerPaddingModifier ->
        NavHost(
            navController = appState.navController,
            startDestination = Routes.HomeScreen.route,
            modifier = Modifier.padding(innerPaddingModifier)
        ){
            TMDBGraph(appState = appState, navController = appState.navController)
        }
    }
}

@Composable
fun rememebrAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackBarManager = SnackBarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(scaffoldState, navController, snackbarManager, resources, coroutineScope){
    TMDBAppState(scaffoldState,navController,snackbarManager,resources,coroutineScope)
}

@Composable
fun resources(): Resources{
    return LocalContext.current.resources
}

fun NavGraphBuilder.TMDBGraph(appState: TMDBAppState, navController: NavHostController){

    composable(Routes.HomeScreen.route){
        HomeScreen(
            navController = navController,
            onNavigateToMovieDetails = { movieId ->
            navController.navigate("${Routes.MovieDetail.route}/$movieId")
        },
            onNavigateToShowDetails = {tvShowId ->
                navController.navigate("${Routes.ShowDetail.route}/$tvShowId")
            }
        )
    }
    composable(BottomNavigationScreens.Home.route){
        HomeScreen(
            navController = navController,
            onNavigateToMovieDetails = {
            navController.navigate(Routes.MovieDetail.route)
        },
            onNavigateToShowDetails = {tvShowId ->
                navController.navigate("${Routes.ShowDetail.route}/$tvShowId")
            }
        )
    }
    composable(BottomNavigationScreens.Search.route){
        SearchScreen()
    }
    composable(BottomNavigationScreens.Favorite.route){
        FavoriteScreen()
    }
    composable(
//        "${Routes.MovieDetail.route}/{${Routes.MovieDetail.Args.movieId}}",
        "${Routes.MovieDetail.route}/{$MOVIE_DETAIL_ID_KEY}",
        arguments = listOf(
            navArgument(MOVIE_DETAIL_ID_KEY){type = NavType.IntType}
        )
    ){
//        val movieId = it.arguments?.getString(Routes.MovieDetail.Args.movieId)?.toInt()
//            ?: throw IllegalStateException("Missing movieId argument")
        MovieDetailScreen()
    }

    composable(
        "${Routes.ShowDetail.route}/{$TV_SHOWDETAIL_ID_KEY}",
        arguments = listOf(
            navArgument(TV_SHOWDETAIL_ID_KEY){type = NavType.IntType}
        )
    ){
        TvDetailScreen()
    }

}



