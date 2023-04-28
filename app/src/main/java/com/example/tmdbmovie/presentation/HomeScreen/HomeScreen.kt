package com.example.tmdbmovie.presentation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tmdbmovie.composables.*
import com.example.tmdbmovie.presentation.TrendingScreen.TrendingScreen
import com.example.tmdbmovie.ui.theme.SoftYellow


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
    modifier: Modifier = Modifier
){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

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

    //extracting from state
    val popularMovies = popularMoviesState.value
    val topRatedMovies = topRatedMovieState.value
    val nowPlayingMovies = nowPlayingMovieState.value
    val upComingMovies = upcomingMovieState.value
    val trendingMovies = trendingMoviesState.value

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


    println("The trending movies are: ${trendingMovies?.size} yes, yes")
    println("The trending shows are: ${trendingShows?.size} no, yes")

    Column (
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 4.dp)
            .verticalScroll(scrollState)
    ){


        Spacer(modifier = Modifier.height(12.dp))
//        TMDBSearchBar(onSearch = {})
//        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Trending:", style = MaterialTheme.typography.h3)
        Spacer(modifier = Modifier.height(12.dp))
        if (trendingMovies != null) {
            EmptyCard2(trendingMovies = trendingMovies)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Movies:", style = MaterialTheme.typography.h3)


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
                                MaterialTheme.colors.secondary
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
                    TabCardMovieContent( images = popularMovies)
                }else{
                    EmptyCard()
                }
            }

            1 -> {

                if (topRatedMovies != null) {
                    TabCardMovieContent(images = topRatedMovies)
                }else{
                    EmptyCard()
                }

            }

            2 -> {
                if (nowPlayingMovies != null) {
                    TabCardMovieContent(images = nowPlayingMovies)
                }else{
                    EmptyCard()
                }
            }

            3 -> {
                if (upComingMovies != null) {
                    TabCardMovieContent(images = upComingMovies)
                }else{
                    EmptyCard()
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "TV Shows:", style = MaterialTheme.typography.h3)

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
                                MaterialTheme.colors.secondary
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
                    TabCardTvShowContent(images = popularShows)
                }else{
                    EmptyCard()
                }
            }

            1 -> {
                if (topRatedShows != null){
                    TabCardTvShowContent(images = topRatedShows)
                }else{
                    EmptyCard()
                }
            }

            2 -> {
                if (onAirShows != null){
                    TabCardTvShowContent(images = onAirShows)
                }else{
                    EmptyCard()
                }
            }

        }




        Spacer(modifier = Modifier.height(70.dp))

    }

}







