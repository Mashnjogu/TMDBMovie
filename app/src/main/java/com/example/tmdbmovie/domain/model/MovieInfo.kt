package com.example.tmdbmovie.domain.model

import com.example.tmdbmovie.data.model.genre.Genre

data class MovieInfo(
    val id: Int,
    val title: String,
    val backdrop_path: String?,
    val genreIds: List<Int>,
    val genres: List<Genre>,
    val original_language: String,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double,
    val vote_count: Int,
    val credits: Credits,
    val videos: VideoList,
    val similar: MovieList,
    val runtime: Int?,
){
    fun trimGenreList() = genres.joinToString { it.name }

    companion object{
        val empty = MovieInfo(
            id = 0,
            title = "",
            backdrop_path = null,
            genreIds = emptyList(),
            genres = emptyList(),
            original_language = "",
            overview = "",
            poster_path = null,
            release_date = null,
            vote_average = 0.0,
            vote_count = 0,
            credits = Credits.empty,
            videos = VideoList.empty,
            similar = MovieList.empty,
            runtime = null
        )
    }
}

