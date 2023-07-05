package com.example.tmdbmovie.composables

import android.widget.ImageView
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import coil.load
import com.example.tmdbmovie.data.model.genre.Genre
import com.example.tmdbmovie.data.model.movies.MovieDataDTO
import com.example.tmdbmovie.data.model.tvshows.TvShowDataDTO
import com.example.tmdbmovie.domain.util.BACKDROPPATHURL
import com.example.tmdbmovie.extras.TrendingMediaData
import com.flaviofaria.kenburnsview.KenBurnsView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import java.util.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun ScrollEffectChange(
    modifier: Modifier = Modifier,
    trendingMovies: List<MovieDataDTO>,
    onNavigateToMovieDetails: (Int) -> Unit
){

    val pagerState = rememberPagerState(
        pageCount = trendingMovies.size,
        initialOffscreenLimit = 2
    )

    
    LaunchedEffect(Unit){
        while (true){
            yield()
            delay(7000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxSize()
    ) {pageIndex ->

        val trendingMovie = trendingMovies[pageIndex]

        val trendingImage = "${BACKDROPPATHURL}${trendingMovie.backdrop_path}"
        val trendingMovieName = trendingMovie.title
        val trendingMovieOverview = trendingMovie.overview
        val genres = trendingMovie.genre_ids
        val trendingMovieId = trendingMovie.id

        Card(
            modifier = modifier
                .fillMaxSize()
                .clickable{
                    onNavigateToMovieDetails(trendingMovieId)
                }
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(pageIndex).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                }
//                .padding(start = 12.dp, end = 12.dp),
                    ,
            shape = RoundedCornerShape(24.dp),

        ) {
            Box{
                val kenBurnsView = KenBurnsView(LocalContext.current).also { imageView ->
                    imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                    imageView.load(trendingImage)
                }

                AndroidView(
                    factory = {kenBurnsView},
                    modifier = modifier.fillMaxSize()
                )

                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(color = Color(android.graphics.Color.parseColor("#80000000")))
                ){

                }

                Column(
                    modifier = modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ){

                    Text(text = trendingMovieName, style =androidx.compose.material.MaterialTheme.typography.h3, color = Color.White)

                    Spacer(modifier = Modifier.height(6.dp))

                }
            }
        }
    }
}






