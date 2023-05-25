package com.example.tmdbmovie.composables

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdbmovie.R
import com.example.tmdbmovie.domain.util.POSTERPATHURL

@Composable
fun CastProfileCard(){
    Card {
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