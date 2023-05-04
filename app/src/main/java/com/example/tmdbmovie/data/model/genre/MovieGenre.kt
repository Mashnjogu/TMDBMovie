package com.example.tmdbmovie.data.model.genre

import com.example.tmdbmovie.data.model.genre.Genre
import com.google.gson.annotations.SerializedName

data class MovieGenre(
    @SerializedName("genres")
    val genres: List<Genre>
)
