package com.example.tmdbmovie.extras

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieID(
    val movieId: Int
): Parcelable
