package com.example.tmdbmovie.composables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdbmovie.R
import com.example.tmdbmovie.data.model.movies.MovieDataDTO
import com.example.tmdbmovie.data.model.search.Search
import com.example.tmdbmovie.domain.util.BACKDROPPATHURL
import com.example.tmdbmovie.extras.Routes

@Composable
fun SearchItem(
    searchItem: Search,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    navController: NavController
){

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp


    androidx.compose.material.Card(
        modifier = modifier
            .fillMaxWidth()
            .height(
                screenHeight / 7
            )
            .aspectRatio(1f),
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp)
    ){
        Row(modifier = modifier.padding(8.dp)) {
            Card(
                modifier = modifier.clickable(
                    onClick = {
//                        navController.navigate("${Routes.ShowDetail.route}/${searchItem.id}")
                        if (searchItem.name.isNullOrEmpty()) {
                            // Go to series
                            navController.navigate("${Routes.ShowDetail.route}/${searchItem.id}")
                        } else{
                            navController.navigate("${Routes.MovieDetail.route}/${searchItem.id}")
                        }
                })
            ){
                AsyncImage(
                    error = painterResource(id = R.drawable.ic_broken_image),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("$BACKDROPPATHURL${searchItem.backdropPath}")
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(id = R.string.filmPhoto),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column ( horizontalAlignment = Alignment.End){
                if (searchItem.title == null){
                    Text(
                        text = "${searchItem.name}",
                        style = MaterialTheme.typography.h4,
                        color = if (isDarkTheme) Color.White else Color.Black
                    )
                }else if(searchItem.name == null){
                    Text(
                        text = "${searchItem.title}",
                        style = MaterialTheme.typography.h4,
                        color = if (isDarkTheme) Color.White else Color.Black
                    )
                }
            }
        }

    }
}

