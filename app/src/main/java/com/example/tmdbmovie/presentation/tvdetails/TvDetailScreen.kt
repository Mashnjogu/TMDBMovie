package com.example.tmdbmovie.presentation.tvdetails


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdbmovie.R
import com.example.tmdbmovie.composables.CastProfileCard
import com.example.tmdbmovie.composables.GenreChip
import com.example.tmdbmovie.composables.TabCardMovieContent
import com.example.tmdbmovie.composables.TabCardTvShowContent
import com.example.tmdbmovie.domain.model.Person
import com.example.tmdbmovie.domain.util.BACKDROPPATHURL
import com.example.tmdbmovie.extras.TvShowDetailsUiState

private val headerHeight = 420.dp
private val toolbarHeight = 56.dp
private val paddingMedium = 16.dp
private val titlePaddingStart = 16.dp
private val titlePaddingEnd = 72.dp
private const val titleFontScaleStart = 1f
private const val titleFontScaleEnd = 0.66f

@Composable
fun TvDetailScreen(
    modifier: Modifier = Modifier,
){
    val tvShowDetailViewModel = hiltViewModel<TvDetailsViewModel>()
    val scroll: ScrollState = rememberScrollState(0)
    val headerHeightPx = with(LocalDensity.current){
        headerHeight.toPx()
    }
    val toolbarHeightPx = with(LocalDensity.current){
        toolbarHeight.toPx()
    }
    Box(modifier = modifier.fillMaxSize()){
        Header(headerHeightPx = headerHeightPx, scroll = scroll, viewModel = tvShowDetailViewModel)
        Body(scrollState = scroll, viewModel = tvShowDetailViewModel)
        Toolbar(scroll = scroll, headerHeightPx = headerHeightPx, toolbarHeightPx = toolbarHeightPx)
        Title(scroll = scroll, headerHeightPx = headerHeightPx, toolbarHeightPx = toolbarHeightPx,viewModel = tvShowDetailViewModel)
    }
}


@Composable
private fun Header(modifier: Modifier = Modifier, headerHeightPx: Float, scroll: ScrollState, viewModel: TvDetailsViewModel){

    val uiState by viewModel.tvDetailsUiState.collectAsState()


    Box(modifier = modifier
        .fillMaxWidth()
        .height(headerHeight)
        .graphicsLayer {
            alpha = (-1f / headerHeightPx) * scroll.value + 1
        }
    ){

        when(val currentState = uiState){
            is TvShowDetailsUiState.Loading ->{
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
            is TvShowDetailsUiState.Success -> {
                val showImage = currentState.showDetails.backdrop_path

                Log.d("DetailScreen=>", "The movie image is $showImage and the movie name is ${currentState.showDetails.name}")

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
                            .data("$BACKDROPPATHURL${showImage}")
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
            is TvShowDetailsUiState.Error -> {}
        }
    }
}

@Composable
fun Body(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    viewModel: TvDetailsViewModel,
) {

    val uiState by viewModel.tvDetailsUiState.collectAsState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(10.dp)
    ) {
        Spacer(Modifier.height(headerHeight))


        when (val currentState = uiState) {
            is TvShowDetailsUiState.Loading -> {
                CircularProgressIndicator()
            }
            is TvShowDetailsUiState.Success -> {


                val showDetails = currentState.showDetails
                val configuration = LocalConfiguration.current
                val screenWidth = configuration.screenWidthDp.dp
                val screenHeight = configuration.screenHeightDp.dp
                val similarMoviesHeight = screenHeight * 0.27f

                val genreNames = showDetails.genres.map {
                    it.name
                }

                val showCast = showDetails.credits.cast.map {
                    Person(
                        character = it.character,
                        department = it.department,
                        id = it.id,job = null,
                        knownForDepartment = it.knownForDepartment,
                        name = it.name,
                        profilePath = it.profilePath
                    )
                }

                val similarShows = showDetails.similar.results

                Row(
                    modifier = modifier.padding(PaddingValues(horizontal = 8.dp)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Column(){
                        Text(text = "Released on : ${showDetails.firstAirDate}",
                            style = MaterialTheme.typography.h5 )
                        Text(
                            text = "Rating: ${showDetails.vote_average}",
                            style = MaterialTheme.typography.h5
                        )
                    }
                    Spacer(modifier = Modifier.width(screenWidth * 0.25f))
                    Card(modifier = modifier
                        .height(80.dp)
                        .width(85.dp)

                    ){
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "",
                            modifier = modifier.padding(4.dp))
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(text = "No of Seasons:", style = androidx.compose.material.MaterialTheme.typography.h5)
                    Text(text = "${showDetails.num_of_seasons}", style = androidx.compose.material.MaterialTheme.typography.h5)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(text = "No of episodes:", style = androidx.compose.material.MaterialTheme.typography.h5)
                    Text(text = "${showDetails.num_of_episodes}", style = androidx.compose.material.MaterialTheme.typography.h5)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(text = "Created By: ", style = androidx.compose.material.MaterialTheme.typography.h5)
                    Text(text = "${showDetails.created_by}", style = androidx.compose.material.MaterialTheme.typography.h5)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Genre:", style = androidx.compose.material.MaterialTheme.typography.h5)

                Spacer(modifier = Modifier.height(8.dp))
                LazyRow( horizontalArrangement = Arrangement.spacedBy(6.dp)){
                    items(genreNames){
                        GenreChip(it)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Summary:",
                    style = androidx.compose.material.MaterialTheme.typography.h5,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${showDetails.overview}",  style = androidx.compose.material
                    .MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(text = "Still in Production:", style = androidx.compose.material.MaterialTheme.typography.h5)
                    Text(text = "${showDetails.in_production}", style = androidx.compose.material.MaterialTheme.typography.h5)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Cast:",  style = androidx.compose.material.MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)){
                    items(items = showCast, key = {person -> person.id }){
                        CastProfileCard(personDetail = it)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(text = "Trailers:",  style = androidx.compose.material.MaterialTheme.typography.h5)
                    Text(text = "See more",  style = androidx.compose.material.MaterialTheme.typography.h5)
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Similar shows:",  style = androidx.compose.material.MaterialTheme.typography.h5)
                Text("Grid of 3")
                TabCardTvShowContent(images = similarShows, onNavigateToTvShowDetails = {}, screenHeight = similarMoviesHeight)
                Spacer(modifier = Modifier.height(12.dp))
            }
            is TvShowDetailsUiState.Error -> {}
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
    viewModel: TvDetailsViewModel,
) {

    val showDetailState = viewModel.tvShowDetails.collectAsState()
    val showTitle = showDetailState.value?.name

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
        text = showTitle ?: "Cannot Retrieve Title",
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