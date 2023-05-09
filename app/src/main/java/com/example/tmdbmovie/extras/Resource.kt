package com.example.tmdbmovie.extras

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Resource<out T>{
    data class Success<out T>(val data: T): Resource<T>()
    data class Error(val message: String): Resource<Nothing>()
}


@Parcelize
enum class MediaType : Parcelable {
    MOVIE,TV, PERSON
}