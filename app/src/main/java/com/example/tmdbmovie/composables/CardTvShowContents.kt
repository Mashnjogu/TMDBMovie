package com.example.tmdbmovie.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdbmovie.R
import com.example.tmdbmovie.data.model.movies.MovieDataDTO
import com.example.tmdbmovie.data.model.tvshows.TvShowDataDTO
import com.example.tmdbmovie.domain.util.POSTERPATHURL

@Composable
fun TabCardTvShowContent(
    modifier: Modifier = Modifier,
    images: List<TvShowDataDTO>,
    onNavigateToTvShowDetails: (Int) -> Unit,
    screenHeight: Dp
){
    val configuration = LocalConfiguration.current


    Card(
        modifier =
        modifier
            .fillMaxWidth()
            .height(screenHeight),

        ) {

        FilmListShowImages(films = images, onNavigateToTvShowDetails = onNavigateToTvShowDetails)
    }
}

@Composable
fun FilmListShowImages(
    films: List<TvShowDataDTO>,
    modifier: Modifier = Modifier,
    onNavigateToTvShowDetails: (Int) -> Unit
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ){
        items(items = films, key = {film -> film.id}){ film ->
            FilmShowImageCard(film = film, onNavigateToTvShowDetails = {onNavigateToTvShowDetails(film.id)})
        }
    }
}

@Composable
fun FilmShowImageCard(
    film: TvShowDataDTO,
    modifier: Modifier = Modifier,
    onNavigateToTvShowDetails: () -> Unit
){
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onNavigateToTvShowDetails),
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        AsyncImage(
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data("$POSTERPATHURL${film.poster_path}")
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.filmPhoto),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun ShowTrendingCardContent(modifier: Modifier = Modifier, tvShowData: TvShowDataDTO){
    Text(text = tvShowData.name)
    Text(text = tvShowData.overview)
}
