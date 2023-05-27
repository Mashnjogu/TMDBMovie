package com.example.tmdbmovie.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdbmovie.R
import com.example.tmdbmovie.domain.model.Person
import com.example.tmdbmovie.domain.util.BACKDROPPATHURL
import com.example.tmdbmovie.domain.util.POSTERPATHURL
import com.example.tmdbmovie.domain.util.dummyImage

@Composable
fun CastProfileCard(
    modifier: Modifier = Modifier,
    personDetail: Person
){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    androidx.compose.material.Card (
        modifier = modifier
//            .height(screenHeight * 0.5f)
//            .width(screenWidth * 0.5f)
            .aspectRatio(1f),
        elevation = 8.dp,
        shape = RoundedCornerShape(25.dp)
    ){
        Column(
            modifier = modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Card(
                modifier = modifier.padding(6.dp)

                    .height(screenHeight * 0.2f)
                    .width(screenWidth * 0.35f),
                shape = RoundedCornerShape(100.dp)
            ){
                AsyncImage(
                    error = painterResource(id = R.drawable.ic_broken_image),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("$BACKDROPPATHURL${personDetail.profilePath}")
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(id = R.string.filmPhoto),
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(modifier = modifier.height(4.dp))
            personDetail.character?.let { Text(text = it) }
            Spacer(modifier = modifier.height(4.dp))
            Text(text = personDetail.name)
            Spacer(modifier = modifier.height(6.dp))

        }


        }



}

val test = Person(
    character = "Actor",
    department = "Entertainment",
    id = 2,job = null,
    knownForDepartment = "Tv shows",
    name = "Dennis",
    profilePath = "/nLBRD7UPR6GjmWQp6ASAfCTaWKX.jpg"
)
@Composable
@Preview
fun CastProfileCardPreview(){
    CastProfileCard(personDetail = test)
}