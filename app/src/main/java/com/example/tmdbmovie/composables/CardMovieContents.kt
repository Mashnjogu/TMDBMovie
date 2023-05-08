package com.example.tmdbmovie.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdbmovie.data.model.movies.MovieDataDTO
import com.example.tmdbmovie.R
import com.example.tmdbmovie.data.model.genre.Genre
import com.example.tmdbmovie.domain.util.POSTERPATHURL

@Composable
fun TabCardMovieContent(
    modifier: Modifier = Modifier,
    images: List<MovieDataDTO>,
    onNavigateToMovieDeatils: (Int) -> Unit
){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Card(
        modifier =
        modifier
            .fillMaxWidth()
            .height(screenHeight * 0.35f),

    ) {

        FilmListImages(films = images, onNavigateToMovieDeatils = onNavigateToMovieDeatils)
    }
}

@Composable
fun FilmListImages(
    films: List<MovieDataDTO>,
    modifier: Modifier = Modifier,
    onNavigateToMovieDeatils: (Int) -> Unit
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ){
        items(items = films, key = {film -> film.id}){ film ->
            FilmImageCard(film = film, onNavigateToMovieDetails = {onNavigateToMovieDeatils(film.id)})
        }
    }
}

@Composable
fun FilmImageCard(
    film: MovieDataDTO,
    modifier: Modifier = Modifier,
    onNavigateToMovieDetails: () -> Unit
){
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onNavigateToMovieDetails),
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        AsyncImage(
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data("${POSTERPATHURL}${film.poster_path}")
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.filmPhoto),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun EmptyCard(modifier: Modifier = Modifier){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Card(
        modifier =
        modifier
            .fillMaxWidth()
            .height(screenHeight * 0.35f),
        backgroundColor = Color.LightGray
    ) {

//        AsyncImage(
//            model = painterResource(id = R.drawable.ic_broken_image),
//            contentDescription =  stringResource(id = R.string.filmPhoto)
//        )

    }
}

@Composable
fun EmptyCard2(
    modifier: Modifier = Modifier,
    trendingMovies: List<MovieDataDTO>,
    allGenres: List<Genre>
){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Card(
        modifier =
        modifier
            .fillMaxWidth()
            .height(screenHeight * 0.45f),
    ) {
        ScrollEffectChange(trendingMovies = trendingMovies, allGenres = allGenres)
    }
}



























