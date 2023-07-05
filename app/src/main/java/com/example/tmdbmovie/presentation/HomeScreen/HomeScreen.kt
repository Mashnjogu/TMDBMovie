package com.example.tmdbmovie.presentation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tmdbmovie.composables.*
import com.example.tmdbmovie.ui.theme.SoftYellow


@Composable
fun HomeScreen(
    navController: NavHostController,
    onNavigateToMovieDetails: (Int) -> Unit,
    onNavigateToShowDetails: (Int) -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme()
){

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                 TopAppBar(
                     title = {
                         Text(
                             text = "MovieOnline",
                             style = MaterialTheme.typography.h3,
                             color = if (isDarkTheme) Color.White else Color.Black
                         )
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
            Home(
                paddingValues = paddingValues,
                scrollState = scrollState,
                onNavigateToMovieDetails = onNavigateToMovieDetails,
                onNavigateToShowDetails = onNavigateToShowDetails
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
    )
}

@Composable
fun Home(
    paddingValues: PaddingValues,
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
    onNavigateToMovieDetails: (Int) -> Unit,
    onNavigateToShowDetails: (Int) -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme()
){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val movieScreenHeight = screenHeight * 0.35f

    val dropdownMenuOption = listOf<String>("Movies", "TV Shows")

    var tabIndexMovie by remember{ mutableStateOf(0) }
    var tabIndexShow by remember{ mutableStateOf(0) }

    val tabs = listOf("Popular", "Top Rated", "Now Playing", "Upcoming")
    val tabShows = listOf("Popular", "Top Rated", "On Air")

    val moviesViewModel = hiltViewModel<MoviesViewModel>()
    val tvShowsViewModel = hiltViewModel<TVShowsViewModel>()

    //*************MOVIES*****************
    //collecting states
    val popularMoviesState = moviesViewModel.uiStatePopular.collectAsState()
    val topRatedMovieState = moviesViewModel.uiStateTopRated.collectAsState()
    val nowPlayingMovieState =  moviesViewModel.uiStateNowPlaying.collectAsState()
    val upcomingMovieState =  moviesViewModel.uiStateUpcoming.collectAsState()
    val trendingMoviesState = moviesViewModel.uiStateTrending.collectAsState()
    val movieGenresState = moviesViewModel.uiStateGenres.collectAsState()

    //extracting from state
    val popularMovies = popularMoviesState.value
    val topRatedMovies = topRatedMovieState.value
    val nowPlayingMovies = nowPlayingMovieState.value
    val upComingMovies = upcomingMovieState.value
    val trendingMovies = trendingMoviesState.value
    val movieGenres = movieGenresState.value

    //*************TVSHOWS***************
    //collecting states
    val popularShowsState = tvShowsViewModel.uiStatePopularShows.collectAsState()
    val topRatedShowsState = tvShowsViewModel.uiStateTopRatedShows.collectAsState()
    val onAirShowsState = tvShowsViewModel.uiStateOnAirShows.collectAsState()
    val trendingShowsState = tvShowsViewModel.uiStateTrending.collectAsState()

    //extracting states
    val popularShows = popularShowsState.value
    val topRatedShows = topRatedShowsState.value
    val onAirShows = onAirShowsState.value
    val trendingShows = trendingShowsState.value

    val trendingFilmsSize = (trendingMovies?.size ?: 0) + (trendingShows?.size ?: 0)

//    println("The list of movie genres are : ${movieGenres?.size}")
    println("The popular movies are: ${popularMovies}")
    println("The popular shows are: ${popularShows}")
    println("The top rated movies are: ${topRatedMovies?.size}")

    Column (
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 4.dp)
            .verticalScroll(scrollState)
    ){


        Spacer(modifier = Modifier.height(12.dp))
//        TMDBSearchBar(onSearch = {})
//        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Trending Movies:",
            style = MaterialTheme.typography.h3,
            color = if (isDarkTheme) Color.White else Color.Black
        )
        Spacer(modifier = Modifier.height(12.dp))
        if (trendingMovies != null) {
            if (movieGenres != null) {
                TrendingMovieCard(trendingMovies = trendingMovies, onNavigateToMovieDetails = onNavigateToMovieDetails)
            }
        }else{
            EmptyCard()
        }
        Spacer(modifier = Modifier.height(12.dp))
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Movies:",
            style = MaterialTheme.typography.h3,
            color = if (isDarkTheme) Color.White else Color.Black
        )


      ScrollableTabRow(
            selectedTabIndex = tabIndexMovie,
          edgePadding = 0.dp,
          indicator = { tabPositions ->
              TabRowDefaults.Indicator(
                  color = SoftYellow,
                  height = 2.dp,
                  modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndexMovie])
              )
          }
        ) {
            tabs.forEachIndexed{ index, title ->
                val selected = tabIndexMovie == index
                Tab(
                    text = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.h5,
                            color = if (selected){
                                SoftYellow
                            }else{
                                if(isDarkTheme){
                                    Color.White
                                }else{
                                    Color.Black
                                }

                            }

                        )},
                    selected = selected,
                    selectedContentColor = SoftYellow,
                    onClick = { tabIndexMovie = index})
            }
        }

        when(tabIndexMovie){
            0 -> {
                if (popularMovies != null) {
                    TabCardMovieContent( images = popularMovies, onNavigateToMovieDeatils = onNavigateToMovieDetails, screenHeight = movieScreenHeight)
                }else{
                    EmptyCard()
                }
            }

            1 -> {

                if (topRatedMovies != null) {
                    TabCardMovieContent(images = topRatedMovies,onNavigateToMovieDeatils = onNavigateToMovieDetails, screenHeight = movieScreenHeight)
                }else{
                    EmptyCard()
                }

            }

            2 -> {
                if (nowPlayingMovies != null) {
                    TabCardMovieContent(images = nowPlayingMovies, onNavigateToMovieDeatils = onNavigateToMovieDetails, screenHeight = movieScreenHeight)
                }else{
                    EmptyCard()
                }
            }

            3 -> {
                if (upComingMovies != null) {
                    TabCardMovieContent(images = upComingMovies, onNavigateToMovieDeatils = onNavigateToMovieDetails, screenHeight = movieScreenHeight)
                }else{
                    EmptyCard()
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "TV Shows:",
            style = MaterialTheme.typography.h3,
            color = if (isDarkTheme) Color.White else Color.Black
        )

        TabRow(
            selectedTabIndex = tabIndexShow,
//            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = SoftYellow,
                    height = 2.dp,
                    modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndexShow])
                )
            }
        ) {
            tabShows.forEachIndexed{ index, title ->
                val selected = tabIndexShow == index
                Tab(
                    text = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.h5,
                            color = if (selected){
                                SoftYellow
                            }else{
                                if(isDarkTheme){
                                    Color.White
                                }else{
                                    Color.Black
                                }
                            }

                        )},
                    selected = selected,
                    selectedContentColor = SoftYellow,
                    onClick = { tabIndexShow = index})
            }
        }

        when(tabIndexShow){
            0 -> {
                if (popularShows != null){
                    TabCardTvShowContent(images = popularShows, onNavigateToTvShowDetails = onNavigateToShowDetails,screenHeight = movieScreenHeight)
                }else{
                    EmptyCard()
                }
            }

            1 -> {
                if (topRatedShows != null){
                    TabCardTvShowContent(images = topRatedShows, onNavigateToTvShowDetails = onNavigateToShowDetails, screenHeight = movieScreenHeight)
                }else{
                    EmptyCard()
                }
            }

            2 -> {
                if (onAirShows != null){
                    TabCardTvShowContent(images = onAirShows, onNavigateToTvShowDetails = onNavigateToShowDetails, screenHeight = movieScreenHeight)
                }else{
                    EmptyCard()
                }
            }

        }




        Spacer(modifier = Modifier.height(70.dp))

    }

}







