package com.example.tmdbmovie.data.model.movies

import com.example.tmdbmovie.data.model.credits.CreditsDTO
import com.example.tmdbmovie.data.model.genre.MovieGenreDTO
import com.example.tmdbmovie.data.model.videos.VideoListDTO
import com.google.gson.annotations.SerializedName

data class MovieDetailDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("genres")
    val genres: List<MovieGenreDTO>,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("release_date")
    val release_date: String?,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int,
    @SerializedName("credits")
    val credits: CreditsDTO,
    @SerializedName("videos")
    val videos: VideoListDTO,
    @SerializedName("similar")
    val similar: MoviesDTO,
    @SerializedName("runtime")
    val runtime: Int?,
)
