package com.example.tmdbmovie.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdbmovie.R
import com.example.tmdbmovie.data.local.entity.FavoriteMovieEntity
import com.example.tmdbmovie.data.local.entity.FavoriteTvEntity
import com.example.tmdbmovie.domain.util.BACKDROPPATHURL

@Composable
fun FavoriteTvItem(
    modifier: Modifier = Modifier,
    favoriteTv: FavoriteTvEntity,
    isDarkTheme: Boolean = isSystemInDarkTheme()
){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(screenHeight * 0.23f)
            .clickable {}
    ){
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Row{
                Card(modifier = modifier
                    .padding(6.dp)
                    .fillMaxHeight()
                    .width(screenWidth * 0.35f),
//                    shape = RoundedCornerShape(10.dp)
                ){
                    Box( modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)){
                        AsyncImage(
                            error = painterResource(id = R.drawable.ic_broken_image),
                            placeholder = painterResource(id = R.drawable.loading_img),
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data("$BACKDROPPATHURL${favoriteTv.backdropPath}")
                                .crossfade(true)
                                .build(),
                            contentDescription = stringResource(id = R.string.fav_film),
                            contentScale = ContentScale.Crop,
                            modifier = modifier.fillMaxHeight()
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = favoriteTv.name,
                        style = androidx.compose.material.MaterialTheme.typography.h4,
                        color = if (isDarkTheme) Color.White else Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${favoriteTv.numOfSeasons} seasons available",
                        style = androidx.compose.material.MaterialTheme.typography.h5,
                        color = if (isDarkTheme) Color.White else Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Rated: ${favoriteTv.voteAverage}",
                        style = androidx.compose.material.MaterialTheme.typography.h5,
                        color = if (isDarkTheme) Color.White else Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Released on: ${favoriteTv.firstAirDate}",
                        style = androidx.compose.material.MaterialTheme.typography.h5,
                        color = if (isDarkTheme) Color.White else Color.Black
                    )
                }
            }
        }

    }
}

@Composable
fun FavoriteMovieItem(
    modifier: Modifier = Modifier,
    favoriteMovie: FavoriteMovieEntity,
    isDarkTheme: Boolean = isSystemInDarkTheme()
){

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(screenHeight * 0.23f)
            .clickable {}
    ){
        Box(
            modifier = Modifier.fillMaxSize()

        ){
            Row{
                Card(modifier = modifier
                    .padding(6.dp)
                    .fillMaxHeight()
                    .width(screenWidth * 0.35f),
//                    shape = RoundedCornerShape(10.dp)
                ){
                    Box( modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)){
                        AsyncImage(
                            error = painterResource(id = R.drawable.ic_broken_image),
                            placeholder = painterResource(id = R.drawable.loading_img),
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data("$BACKDROPPATHURL${favoriteMovie.backdropPath}")
                                .crossfade(true)
                                .build(),
                            contentDescription = stringResource(id = R.string.fav_film),
                            contentScale = ContentScale.Crop,
                            modifier = modifier.fillMaxHeight()
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = favoriteMovie.title,
                        style = androidx.compose.material.MaterialTheme.typography.h4,
                        color = if (isDarkTheme) Color.White else Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Released on: ${favoriteMovie.releaseDate}",
                        style = androidx.compose.material.MaterialTheme.typography.h5,
                        color = if (isDarkTheme) Color.White else Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Runtime: ${favoriteMovie.runtime}",
                        style = androidx.compose.material.MaterialTheme.typography.h5,
                        color = if (isDarkTheme) Color.White else Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Rated: ${favoriteMovie.voteAverage}",
                        style = androidx.compose.material.MaterialTheme.typography.h5,
                        color = if (isDarkTheme) Color.White else Color.Black
                    )
                }
            }
        }

    }
}