package com.example.tmdbmovie.presentation.moviedetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdbmovie.R
import com.example.tmdbmovie.data.model.movies.MovieDataDTO
import com.example.tmdbmovie.domain.util.dummyImage
import com.example.tmdbmovie.extras.Routes

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
    movieId: Int
) {

    val scroll: ScrollState = rememberScrollState(0)
    val headerHeightPx = with(LocalDensity.current){
        headerHeight.toPx()
    }
    val toolbarHeightPx = with(LocalDensity.current){
        toolbarHeight.toPx()
    }
    Box(modifier = modifier.fillMaxSize()){
        Header(headerHeightPx = headerHeightPx, scroll = scroll)
        Body(scrollState = scroll, movieId = movieId)
        Toolbar(scroll = scroll, headerHeightPx = headerHeightPx, toolbarHeightPx = toolbarHeightPx)
        Title(scroll = scroll, headerHeightPx = headerHeightPx, toolbarHeightPx = toolbarHeightPx)
    }
}

//@Composable
//fun CollapsingToolBar(modifier: Modifier = Modifier){
//
//}


@Composable
private fun Header(modifier: Modifier = Modifier, headerHeightPx: Float, scroll: ScrollState){

    Box(modifier = modifier
        .fillMaxWidth()
        .height(headerHeight)
        .graphicsLayer {
            alpha = (-1f / headerHeightPx) * scroll.value + 1
        }
    ){
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
                    .data(dummyImage)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.filmPhoto),
                contentScale = ContentScale.FillBounds
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
}

@Composable
fun Body(modifier: Modifier = Modifier, scrollState: ScrollState, movieId: Int){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(scrollState)
    ) {
        Spacer(modifier = modifier.height(headerHeight))
        Text(text = "The movie Id is: ${movieId}")
        repeat(5) {
            Text(
                text = "The “Body” composable is as simple as a Column with some Text. But remember, this composable is under the scope of a Box which fills the entire screen (see “CollapsingToolbar” composable above). It means that the “Body” composable overlaps the header while we actually want it to be placed beneath it.\n" +
                        "\n" +
                        "So we are going to use a Spacer (transparent by default) of the same size as the header and fill the remaining space with our Column to do the trick.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun Toolbar(scroll: ScrollState, headerHeightPx: Float, toolbarHeightPx: Float) {

    val toolbarBottom by remember{
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
        exit =  fadeOut(animationSpec = tween(300))
    ){
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
    toolbarHeightPx: Float
) {

    var titleHeightPx by remember {
        mutableStateOf(0f)
    }
    var titleWidthPx by remember { mutableStateOf(0f) }

    val titleHeightDp = with(LocalDensity.current){
        titleHeightPx.toDp()
    }

    Text(
        text = "Nairobi Kenya",
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





