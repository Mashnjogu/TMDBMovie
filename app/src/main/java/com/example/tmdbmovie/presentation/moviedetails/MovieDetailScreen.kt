package com.example.tmdbmovie.presentation.moviedetails


import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdbmovie.R
import com.example.tmdbmovie.composables.CastProfileCard
import com.example.tmdbmovie.composables.GenreChip
import com.example.tmdbmovie.composables.TabCardMovieContent
import com.example.tmdbmovie.data.FavoriteMovie
import com.example.tmdbmovie.data.model.genre.MovieGenreDTO
import com.example.tmdbmovie.domain.model.Person
import com.example.tmdbmovie.domain.util.BACKDROPPATHURL
import com.example.tmdbmovie.extras.MovieDetailsUiState
import com.example.tmdbmovie.presentation.FavoriteScreen.FavMovieViewModel
import com.example.tmdbmovie.presentation.FavoriteScreen.FavTvViewModel

private val headerHeight = 420.dp
private val toolbarHeight = 56.dp
private val paddingMedium = 16.dp
private val titlePaddingStart = 16.dp
private val titlePaddingEnd = 72.dp
private const val titleFontScaleStart = 1f
private const val titleFontScaleEnd = 0.66f

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    onNavigateToMovieDeatils: (Int) -> Unit,
) {
    val movieDetailViewModel = hiltViewModel<MovieDetailsViewModel>()
    val scroll: ScrollState = rememberScrollState(0)
    val headerHeightPx = with(LocalDensity.current){
        headerHeight.toPx()
    }
    val toolbarHeightPx = with(LocalDensity.current){
        toolbarHeight.toPx()
    }
    Box(modifier = modifier.fillMaxSize()){
        Header(headerHeightPx = headerHeightPx, scroll = scroll, viewModel = movieDetailViewModel)
        Body(scrollState = scroll, viewModel = movieDetailViewModel, onNavigateToMovieDeatils = onNavigateToMovieDeatils)
        Toolbar(scroll = scroll, headerHeightPx = headerHeightPx, toolbarHeightPx = toolbarHeightPx)
        Title(scroll = scroll, headerHeightPx = headerHeightPx, toolbarHeightPx = toolbarHeightPx, viewModel = movieDetailViewModel)
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier, headerHeightPx: Float, scroll: ScrollState, viewModel: MovieDetailsViewModel){

    val uiState by viewModel.movieDetailsUiState.collectAsState()

    Box(modifier = modifier
        .fillMaxWidth()
        .height(headerHeight)
        .graphicsLayer {
            alpha = (-1f / headerHeightPx) * scroll.value + 1
        }
    ){
        when(val currentState = uiState){
            is MovieDetailsUiState.Loading ->{
                Card(
                    modifier = modifier
//                .padding(4.dp)
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(Color.Gray),
                    elevation = 8.dp
                ) {
                    CircularProgressIndicator()
                }
            }
            is MovieDetailsUiState.Success -> {
                val movieImage = currentState.movieDetails.backdrop_path

                Card(
                    modifier = modifier
//                .padding(4.dp)
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    elevation = 8.dp,
                ){
                    AsyncImage(
                        error = painterResource(id = R.drawable.ic_broken_image),
                        placeholder = painterResource(id = R.drawable.loading_img),
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data("$BACKDROPPATHURL${movieImage}")
                            .crossfade(true)
                            .build(),
                        contentDescription = stringResource(id = R.string.filmPhoto),
                        contentScale = ContentScale.Crop

                    )

                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color(0xAA000000)),
                                    startY = 3 * headerHeightPx / 4 // to wrap the title only
                                )
                            )
                    )
                }
            }
            is MovieDetailsUiState.Error -> {}
            else -> {}
        }

    }
}

@Composable
fun Body(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    viewModel: MovieDetailsViewModel,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    onNavigateToMovieDeatils: (Int) -> Unit,
) {

    val uiState by viewModel.movieDetailsUiState.collectAsState()

    val movieDetailState = viewModel.movieDetail.collectAsState()
    val movieImage = movieDetailState.value?.backdrop_path

    val favViewModel = hiltViewModel<FavMovieViewModel>()

    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(10.dp)
    ) {
        Spacer(Modifier.height(headerHeight))


        when (val currentState = uiState) {
            is MovieDetailsUiState.Loading -> {
                CircularProgressIndicator()
            }
            is MovieDetailsUiState.Success -> {


                val movieDetails = currentState.movieDetails
                val configuration = LocalConfiguration.current
                val screenWidth = configuration.screenWidthDp.dp
                val screenHeight = configuration.screenHeightDp.dp
                val similarMoviesHeight = screenHeight * 0.27f

                val genreNames = movieDetails.genres.map {
                    it.name
                }

                val movieCast = movieDetails.credits.cast.map {
                    Person(
                        character = it.character,
                        department = it.department,
                        id = it.id,job = null,
                        knownForDepartment = it.knownForDepartment,
                        name = it.name,
                        profilePath = it.profilePath
                    )
                }

                val similarMovies = movieDetails.similar.results

                Row(
                    modifier = modifier.padding(PaddingValues(horizontal = 8.dp)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Column(){
                        Text(text = "Released on : ${movieDetails.release_date}",
                            style = androidx.compose.material.MaterialTheme.typography.h5,
                            color = if (isDarkTheme) Color.White else Color.Black
                        )
                        Text(
                            text = "Rating: ${movieDetails.vote_average}",
                            style = androidx.compose.material.MaterialTheme.typography.h5,
                            color = if (isDarkTheme) Color.White else Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(screenWidth * 0.25f))
                    Card(modifier = modifier
                        .height(80.dp)
                        .width(90.dp)

                    ){
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "",
                            modifier = modifier.padding(4.dp)
                                .clickable {
                                    favViewModel.addFaveMovie(
                                        FavoriteMovie(
                                            id = movieDetails.id,
                                            backdropPath = movieDetails.backdrop_path,
                                            releaseDate = movieDetails.release_date,
                                            runtime = movieDetails.runtime,
                                            title = movieDetails.title,
                                            voteAverage = movieDetails.vote_average,
                                            voteCount = movieDetails.vote_count,
                                            date = System.currentTimeMillis()
                                        )
                                    )
                                }
                        )
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Genre:",
                    style = androidx.compose.material.MaterialTheme.typography.h5,
                    color = if (isDarkTheme) Color.White else Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))
                LazyRow( horizontalArrangement = Arrangement.spacedBy(6.dp)){
                   items(genreNames){
                       GenreChip(it)
                   }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Summary:",
                    style = androidx.compose.material.MaterialTheme.typography.h5,
                    color = if (isDarkTheme) Color.White else Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${movieDetails.overview}",  style = androidx.compose.material
                    .MaterialTheme.typography.h5,
                    color = if (isDarkTheme) Color.White else Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Cast:",
                    style = androidx.compose.material.MaterialTheme.typography.h5,
                    color = if (isDarkTheme) Color.White else Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)){
                    items(items = movieCast, key = {person -> person.id }){
                        CastProfileCard(personDetail = it)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Similar movies:",
                    style = androidx.compose.material.MaterialTheme.typography.h5,
                    color = if (isDarkTheme) Color.White else Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                TabCardMovieContent(images = similarMovies, onNavigateToMovieDeatils = onNavigateToMovieDeatils, screenHeight = similarMoviesHeight)
                Spacer(modifier = Modifier.height(12.dp))
            }
            is MovieDetailsUiState.Error -> {}
            else -> {}
        }

    }

}


    @Composable
    private fun Toolbar(scroll: ScrollState, headerHeightPx: Float, toolbarHeightPx: Float) {

        val toolbarBottom by remember {
            mutableStateOf(headerHeightPx - toolbarHeightPx)
        }

        val showToolbar by remember {
            derivedStateOf {
                scroll.value >= toolbarBottom
            }
        }

        AnimatedVisibility(
            visible = showToolbar,
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            TopAppBar(
                modifier = Modifier.background(
                    brush = Brush.horizontalGradient(
                        listOf(Color(0xff026586), Color(0xff032C45))
                    )
                ),
//        navigationIcon = {
//            IconButton(
//                onClick = {},
//                modifier = Modifier
//                    .padding(horizontal = 16.dp)
//                    .size(36.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Menu,
//                    contentDescription = "",
//                    tint = Color.White
//                )
//            }
//        },
                title = {},
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        }

    }

    @Composable
    private fun Title(
        modifier: Modifier = Modifier,
        scroll: ScrollState, headerHeightPx: Float,
        toolbarHeightPx: Float,
        viewModel: MovieDetailsViewModel,
    ) {

        val movieDetailState = viewModel.movieDetail.collectAsState()
        val movieTitle = movieDetailState.value?.title


        var titleHeightPx by remember {
            mutableStateOf(0f)
        }
        var titleWidthPx by remember { mutableStateOf(0f) }

        val titleHeightDp = with(LocalDensity.current) {
            titleHeightPx.toDp()
        }

        val dominantColor by remember {
            mutableStateOf<Color?>(null)
        }

//        LaunchedEffect(dummyImage) {
//            dominantColor = getDominantColor(imageUrl = dummyImage)
//            println("The dominant color is: ${getDominantColor(imageUrl = dummyImage)}")
//        }


        Text(
            text = movieTitle ?: "Cannot Retrieve Title",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .graphicsLayer {
                    val collapseRange: Float = (headerHeightPx - toolbarHeightPx)
                    val collapseFraction: Float = (scroll.value / collapseRange).coerceIn(0f, 1f)

                    val scaleXY = lerp(
                        titleFontScaleStart.dp,
                        titleFontScaleEnd.dp,
                        collapseFraction
                    )

                    val titleExtraStartPadding = titleWidthPx.toDp() * (1 - scaleXY.value) / 2f

//                Let’s first calculate P0P1 lerp
                    val titleYFirstInterpolatedPoint = lerp(
                        headerHeight - titleHeightDp - paddingMedium,
                        headerHeight / 2,
                        collapseFraction
                    )

                    val titleXFirstInterpolatedPoint = lerp(
                        titlePaddingStart,
                        (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                        collapseFraction
                    )

//                Then let’s calculate P1P2 lerp
                    val titleYSecondInterpolatedPoint = lerp(
                        (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                        toolbarHeight / 2 - titleHeightDp / 2,
                        collapseFraction
                    )

                    val titleXSecondInterpolatedPoint = lerp(
                        titlePaddingEnd * 5 / 4,
                        titlePaddingEnd,
                        collapseFraction
                    )
                    /* let’s calculate the quadratic Bézier curve Z based
                of these two nested “lerps” on the Y axis:
                */

                    val titleY = lerp(
                        titleYFirstInterpolatedPoint,
                        titleYSecondInterpolatedPoint,
                        collapseFraction
                    )

                    val titleX = lerp(
                        titleXFirstInterpolatedPoint,
                        titleXSecondInterpolatedPoint,
                        collapseFraction
                    )

                    translationY = titleY.toPx()
                    translationX = titleX.toPx()
                    scaleX = scaleXY.value
                    scaleY = scaleXY.value
                }
                .onGloballyPositioned {
                    // We don't know title height in advance to calculate the lerp
                    // so we wait for initial composition
                    titleHeightPx = it.size.height.toFloat()
                }
        )
    }

fun extractDominantImage(bitmap: Bitmap): Color? {
    val palette = Palette.from(bitmap).generate()
    val dominantSwatch = palette.dominantSwatch?.rgb
    return dominantSwatch?.let { Color(it) }
}

fun getGenreNames(genres: List<MovieGenreDTO>){

}
